package com.mycompany.projectmanagement;

import com.mycompany.projectmanagement.FileController.Course;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;

public class ExcelController {

    private final UserController userController;
    private final FileController.FileService fileService;
    private final String defaultAvatarPath;
    private final SimpleDateFormat dateFormat;

    public ExcelController() {
        this.userController = new UserController();
        this.fileService = new FileController.FileService();
        this.defaultAvatarPath = "\\src\\main\\java\\com\\mycompany\\projectmanagement\\avatar\\default-avatar-icon-of-social-media-user-vector.jpg";
        this.dateFormat = new SimpleDateFormat("yyyy/MM/dd");
    }

    public static void main(String[] args) throws IOException {
        ExcelController excelController = new ExcelController();
        excelController.processExcelFile("/com/mycompany/projectmanagement/excel/student.xlsx", "student.txt", "student");
    }

    public void processExcelFile(String filePath, String fileName, String role) throws IOException {
        String excelFilePath = getClass().getResource(filePath).getPath();
        try (FileInputStream fis = new FileInputStream(excelFilePath); XSSFWorkbook workbook = new XSSFWorkbook(fis)) {

            XSSFSheet sheet = workbook.getSheetAt(0);
            JSONArray jsonArray = parseSheetToJSON(sheet, role);
            saveDataFromJSON(jsonArray, fileName, role);
        } catch (IOException e) {
        }
    }

    private JSONArray parseSheetToJSON(XSSFSheet sheet, String role) {
        JSONArray jsonArray = new JSONArray();
        int rows = sheet.getPhysicalNumberOfRows();
        XSSFRow headerRow = sheet.getRow(0);
        int cols = headerRow.getPhysicalNumberOfCells();

        for (int r = 1; r < rows; r++) { // Start from the second row assuming the first row is headers
            XSSFRow row = sheet.getRow(r);
            JSONObject jsonObject = new JSONObject();
            String newID = fileService.generateUniqueId(role, "account.txt", "ID");
            jsonObject.put("ID", newID);
            jsonObject.put("ImagePath", defaultAvatarPath);

            for (int c = 0; c < cols; c++) {
                XSSFCell cell = row.getCell(c);
                String key = headerRow.getCell(c).toString();
                jsonObject.put(key, getCellValue(cell));
            }
            jsonArray.put(jsonObject);
        }
        return jsonArray;
    }

    private String getCellValue(XSSFCell cell) {
        if (cell == null) {
            return "";
        }

        if (cell.getCellType() == CellType.NUMERIC) {
            if (DateUtil.isCellDateFormatted(cell)) {
                Date dateCellValue = cell.getDateCellValue();
                return dateFormat.format(dateCellValue);
            } else {
                BigDecimal numericValue = new BigDecimal(cell.getNumericCellValue());
                return numericValue.toPlainString();
            }
        } else {
            return cell.toString();
        }
    }

    private void saveDataFromJSON(JSONArray jsonArray, String fileName, String role) {
        for (Object obj : jsonArray) {
            JSONObject jsonObject = (JSONObject) obj;

            String id = jsonObject.getString("ID");
            String dob = jsonObject.getString("Birth Date");
            if (role.equalsIgnoreCase("student")) {
                String intakeDate = jsonObject.getString("Intake Date");
                String course = jsonObject.getString("Course");
                String entryLevel = jsonObject.getString("Entry Level");

                Course courses = new Course();
                String[] modules = courses.findModule(entryLevel, course);
                String courseId = courses.findCourseID(entryLevel, course);
                createAndSaveAssessment(id, courseId, intakeDate, modules);
            }

            createAndSaveAccount(id, dob, role);
            boolean alreadyExists = fileService.checkExists(fileName, jsonObject,"ID");
            if (!alreadyExists) {
                fileService.write(jsonObject, fileName, true);
            } else {
                JOptionPane.showMessageDialog(null, jsonObject.getString("IC"), "Duplciate Data", JOptionPane.WARNING_MESSAGE);

            }
        }

    }

    private void createAndSaveAccount(String studentID, String dob, String role) {
        UserController.Account account = userController.new Account();
        UserController.User user = userController.new User();
        user.setDob(dob);
        account.setId(studentID);
        account.setAccount(role);
        account.saveTextFile("account.txt");
    }

    private void createAndSaveAssessment(String studentID, String courseId, String intakeDate, String[] modules) {
        FileController.Assessment assessment = new FileController.Assessment();
        assessment.setCourseID(courseId);
        assessment.setStudentID(studentID);
        assessment.setModules(modules);
        assessment.setIntakeDate(intakeDate);
        assessment.saveTextFile("assessment.txt", assessment);
    }
}
