package module12;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

public class ExerciseTest {

    @Test
    void testSRP() {
        OrderValidator validator = new OrderValidator();

        assertTrue(validator.validate("Alice", 99.99), "SRP: valid order returns true");
        assertFalse(validator.validate(null, 50.0), "SRP: null name returns false");
        assertFalse(validator.validate("", 50.0), "SRP: empty name returns false");
        assertFalse(validator.validate("Bob", 0.0), "SRP: zero amount returns false");
        assertFalse(validator.validate("Bob", -10.0), "SRP: negative amount returns false");

        OrderRepository repo = new OrderRepository();
        assertEquals("Saved: Alice - $99.99", repo.save("Alice", 99.99), "SRP: save returns confirmation");

        OrderEmailService email = new OrderEmailService();
        assertEquals("Email sent to: alice@email.com", email.sendConfirmation("alice@email.com"),
                "SRP: sendConfirmation returns message");

        ReceiptPrinter printer = new ReceiptPrinter();
        String receipt = printer.print("Alice", 99.99);
        assertTrue(receipt != null && receipt.contains("Alice"), "SRP: receipt contains customer name");
        assertTrue(receipt != null && receipt.contains("99.99"), "SRP: receipt contains amount");
        assertTrue(receipt != null && receipt.contains("RECEIPT"), "SRP: receipt contains RECEIPT header");
    }

    @Test
    void testOCP() {
        EmailNotifier em  = new EmailNotifier();
        SmsNotifier   sms = new SmsNotifier();
        PushNotifier  psh = new PushNotifier();
        SlackNotifier slk = new SlackNotifier();

        assertEquals("Email: Hello", em.send("Hello"), "OCP: EmailNotifier");
        assertEquals("SMS: Hello", sms.send("Hello"), "OCP: SmsNotifier");
        assertEquals("Push: Hello", psh.send("Hello"), "OCP: PushNotifier");
        assertEquals("Slack: Hello", slk.send("Hello"), "OCP: SlackNotifier");

        NotificationService svc = new NotificationService();
        svc.addNotifier(em);
        svc.addNotifier(sms);
        svc.addNotifier(psh);

        List<String> results = svc.notifyAll("Order shipped!");
        assertEquals(3, results != null ? results.size() : -1, "OCP: notifyAll count = 3");
        assertTrue(results != null && results.contains("Email: Order shipped!"), "OCP: result contains Email");
        assertTrue(results != null && results.contains("SMS: Order shipped!"), "OCP: result contains SMS");
        assertTrue(results != null && results.contains("Push: Order shipped!"), "OCP: result contains Push");

        svc.addNotifier(slk);
        List<String> results2 = svc.notifyAll("Server down!");
        assertEquals(4, results2 != null ? results2.size() : -1, "OCP: notifyAll with 4 notifiers");
    }

    @Test
    void testLSP() {
        GoodRectangle r = new GoodRectangle(5, 3);
        assertEquals(15, r.area(), "LSP: GoodRectangle(5,3).area() = 15");

        GoodSquare s = new GoodSquare(4);
        assertEquals(16, s.area(), "LSP: GoodSquare(4).area() = 16");

        GoodShape[] shapes = { new GoodRectangle(10, 2), new GoodSquare(7) };
        assertEquals(20, shapes[0].area(), "LSP: GoodRectangle(10,2) via GoodShape = 20");
        assertEquals(49, shapes[1].area(), "LSP: GoodSquare(7) via GoodShape = 49");

        BadRectangle bad = new BadSquare();
        bad.setWidth(5);
        bad.setHeight(3);
        assertTrue(bad.area() == 9, "LSP: BadSquare violates contract (area=9, not 15)");
    }

    @Test
    void testISP() {
        BasicPrinter basic = new BasicPrinter();
        assertEquals("Printing: Doc.pdf", basic.print("Doc.pdf"), "ISP: BasicPrinter.print()");
        assertTrue(basic instanceof Printable, "ISP: BasicPrinter is Printable");
        assertFalse(basic instanceof Scannable, "ISP: BasicPrinter NOT Scannable");
        assertFalse(basic instanceof Faxable, "ISP: BasicPrinter NOT Faxable");

        OfficePrinter office = new OfficePrinter();
        assertEquals("Printing: Report.pdf", office.print("Report.pdf"), "ISP: OfficePrinter.print()");
        assertEquals("Scanning: Invoice.pdf", office.scan("Invoice.pdf"), "ISP: OfficePrinter.scan()");
        assertTrue(office instanceof Printable, "ISP: OfficePrinter is Printable");
        assertTrue(office instanceof Scannable, "ISP: OfficePrinter is Scannable");
        assertFalse(office instanceof Faxable, "ISP: OfficePrinter NOT Faxable");

        AllInOnePrinter all = new AllInOnePrinter();
        assertEquals("Printing: Contract.docx", all.print("Contract.docx"), "ISP: AllInOne.print()");
        assertEquals("Scanning: Photo.jpg", all.scan("Photo.jpg"), "ISP: AllInOne.scan()");
        assertEquals("Faxing: Legal.pdf", all.fax("Legal.pdf"), "ISP: AllInOne.fax()");
        assertEquals("Stapling: Report.pptx", all.staple("Report.pptx"), "ISP: AllInOne.staple()");
    }

    @Test
    void testDIP() {
        List<String> data = Arrays.asList("Alice: 95", "Bob: 87");

        ReportService csvSvc  = new ReportService(new CsvExporter());
        ReportService jsonSvc = new ReportService(new JsonExporter());
        ReportService pdfSvc  = new ReportService(new PdfExporter());

        String csvResult  = csvSvc.generateReport(data);
        String jsonResult = jsonSvc.generateReport(data);
        String pdfResult  = pdfSvc.generateReport(data);

        assertTrue(csvResult  != null && csvResult.startsWith("CSV:"),  "DIP: CSV report starts with 'CSV:'");
        assertTrue(jsonResult != null && jsonResult.startsWith("JSON:"), "DIP: JSON report starts with 'JSON:'");
        assertTrue(pdfResult  != null && pdfResult.startsWith("PDF:"),  "DIP: PDF report starts with 'PDF:'");
        assertTrue(csvResult  != null && csvResult.contains("Alice"),    "DIP: reports contain data");

        assertTrue(new CsvExporter()  instanceof ReportExporter, "DIP: CsvExporter is ReportExporter");
        assertTrue(new JsonExporter() instanceof ReportExporter, "DIP: JsonExporter is ReportExporter");
        assertTrue(new PdfExporter()  instanceof ReportExporter, "DIP: PdfExporter is ReportExporter");
    }
}
