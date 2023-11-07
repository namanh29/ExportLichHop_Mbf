package com.example.shinnytest.Miami;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.WorkbookUtil;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.util.*;
import java.util.stream.Collectors;

public class MiamiApplication {

    private static List<HcCaseMeeting> hcMasterCaseExcelReportResultList = new ArrayList<>();

    private static Map<Date, Map<String, List<HcCaseMeeting>>> dayMeetingMap = new HashMap<>();

    public static void main(String[] args) throws IOException, ParseException {
        hcMasterCaseExcelReportResultList = readExcelFile("EOFFICE_HC_CASE_PHONGHOP_REPORT_VIEW_3.xlsx");
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet(WorkbookUtil.createSafeSheetName("LichDatPhongHopMiami"));
        exportExcelFile(hcMasterCaseExcelReportResultList, workbook, sheet, 1617037200000L, 1619802000000L);
    }

    public static List<HcCaseMeeting> readExcelFile(String filePath) throws IOException, ParseException {
        FileInputStream file = new FileInputStream(filePath);
        Workbook workbook = new XSSFWorkbook(file);
        Sheet sheet = workbook.getSheetAt(0);

        List<HcCaseMeeting> excelDataList = new ArrayList<>();

        int rowNum = 0;
        for (Row row : sheet) {
            if (rowNum == 0) {
                rowNum++;
                continue;
            }

            HcCaseMeeting excelData = new HcCaseMeeting();

            excelData.setCaseMasterId(row.getCell(0).getStringCellValue());
            excelData.setFlowInstanceId(row.getCell(1).getStringCellValue());
            excelData.setCaseType(row.getCell(2).getStringCellValue());
            excelData.setCaseTitle(row.getCell(3).getStringCellValue());
            excelData.setRequestUserId(row.getCell(4).getStringCellValue());
            excelData.setRequestDeptName(row.getCell(5).getStringCellValue());
            excelData.setRequestUserFullname(row.getCell(6).getStringCellValue());
            excelData.setRequestRoleId(row.getCell(7).getStringCellValue());
            excelData.setRequestDeptId(row.getCell(8).getStringCellValue());
            excelData.setStatus(row.getCell(9).getStringCellValue());
            excelData.setIsDelete((long) row.getCell(10).getNumericCellValue());

//            excelData.setStartTime(row.getCell(11).getDateCellValue());
            Date startDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSSSS").parse(row.getCell(11).getStringCellValue());
            excelData.setStartTime(startDate);

//            excelData.setEndTime(row.getCell(12).getDateCellValue());
            Date endDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSSSS").parse(row.getCell(12).getStringCellValue());
            excelData.setEndTime(endDate);

            excelData.setUserDeptRoleId(row.getCell(13).getStringCellValue());
            excelData.setHcCasePhonghopId(row.getCell(14).getStringCellValue());
            excelData.setUserId(row.getCell(15).getStringCellValue());
            excelData.setMeetingMemberFullname(row.getCell(16).getStringCellValue());
            excelData.setRoomId(row.getCell(17).getStringCellValue());
            excelData.setServices(H.isTrue(row.getCell(18)) ? row.getCell(18).getStringCellValue() : "");
            excelData.setRoomName(row.getCell(19).getStringCellValue());
//            excelData.setRoomDesc(row.getCell(20).getStringCellValue());
            excelData.setCriticalLevel(row.getCell(21).getStringCellValue());

            Calendar cal = Calendar.getInstance(); // locale-specific
            cal.setTime(startDate);
            cal.set(Calendar.HOUR_OF_DAY, 0);
            cal.set(Calendar.MINUTE, 0);
            cal.set(Calendar.SECOND, 0);
            cal.set(Calendar.MILLISECOND, 0);
            Date startTimeRound = cal.getTime();
            excelData.setStartTimeRound(startTimeRound);

            excelDataList.add(excelData);
        }

        file.close();
        workbook.close();

        return excelDataList;
    }

    public static void exportExcelFile(List<HcCaseMeeting> input, Workbook workbook, Sheet sheet, Long fromTimeInput, Long endTimeInput) throws IOException {
        try {
            if (!H.isTrue(workbook)) workbook = new XSSFWorkbook();

            CellStyle centerAlignmentStyle = workbook.createCellStyle();
            centerAlignmentStyle.setAlignment(HorizontalAlignment.CENTER);
            centerAlignmentStyle.setVerticalAlignment(VerticalAlignment.CENTER);
            centerAlignmentStyle.setWrapText(true);
            centerAlignmentStyle.setBorderTop(BorderStyle.THIN);
            centerAlignmentStyle.setBorderBottom(BorderStyle.THIN);
            centerAlignmentStyle.setBorderLeft(BorderStyle.THIN);
            centerAlignmentStyle.setBorderRight(BorderStyle.THIN);

            CellStyle styleRowHeader = workbook.createCellStyle();
            styleRowHeader.setAlignment(HorizontalAlignment.CENTER);
            styleRowHeader.setVerticalAlignment(VerticalAlignment.CENTER);
            styleRowHeader.setFillForegroundColor(IndexedColors.LIGHT_GREEN.getIndex());
            styleRowHeader.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            styleRowHeader.setBorderTop(BorderStyle.THIN);
            styleRowHeader.setBorderBottom(BorderStyle.THIN);
            styleRowHeader.setBorderLeft(BorderStyle.THIN);
            styleRowHeader.setBorderRight(BorderStyle.THIN);


            CellStyle styleRowData = workbook.createCellStyle();
            styleRowHeader.setAlignment(HorizontalAlignment.CENTER);
            styleRowHeader.setVerticalAlignment(VerticalAlignment.CENTER);
            styleRowHeader.setBorderTop(BorderStyle.THIN);
            styleRowHeader.setBorderBottom(BorderStyle.THIN);
            styleRowHeader.setBorderLeft(BorderStyle.THIN);
            styleRowHeader.setBorderRight(BorderStyle.THIN);

            CellStyle styleColumA = workbook.createCellStyle();
            styleColumA.setAlignment(HorizontalAlignment.CENTER);
            styleColumA.setVerticalAlignment(VerticalAlignment.CENTER);
            styleColumA.setFillForegroundColor(IndexedColors.TURQUOISE.getIndex());
            styleColumA.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            styleColumA.setWrapText(true);
            styleColumA.setBorderTop(BorderStyle.THIN);
            styleColumA.setBorderBottom(BorderStyle.THIN);
            styleColumA.setBorderLeft(BorderStyle.THIN);
            styleColumA.setBorderRight(BorderStyle.THIN);

            CellStyle styleColumBS = workbook.createCellStyle();
            styleColumBS.setAlignment(HorizontalAlignment.CENTER);
            styleColumBS.setVerticalAlignment(VerticalAlignment.CENTER);
            styleColumBS.setFillForegroundColor(IndexedColors.PALE_BLUE.getIndex());
            styleColumBS.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            styleColumBS.setBorderTop(BorderStyle.THIN);
            styleColumBS.setBorderBottom(BorderStyle.THIN);
            styleColumBS.setBorderLeft(BorderStyle.THIN);
            styleColumBS.setBorderRight(BorderStyle.THIN);

            CellStyle styleColumBC = workbook.createCellStyle();
            styleColumBC.setAlignment(HorizontalAlignment.CENTER);
            styleColumBC.setVerticalAlignment(VerticalAlignment.CENTER);
            styleColumBC.setFillForegroundColor(IndexedColors.TAN.getIndex());
            styleColumBC.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            styleColumBC.setBorderTop(BorderStyle.THIN);
            styleColumBC.setBorderBottom(BorderStyle.THIN);
            styleColumBC.setBorderLeft(BorderStyle.THIN);
            styleColumBC.setBorderRight(BorderStyle.THIN);

            CellStyle styleCellKLD = workbook.createCellStyle();
            styleCellKLD.setAlignment(HorizontalAlignment.CENTER);
            styleCellKLD.setVerticalAlignment(VerticalAlignment.CENTER);
            styleCellKLD.setWrapText(true);
//            styleCellKLD.setFillForegroundColor(IndexedColors.AQUA.getIndex());
//            styleCellKLD.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            styleCellKLD.setBorderTop(BorderStyle.THIN);
            styleCellKLD.setBorderBottom(BorderStyle.THIN);
            styleCellKLD.setBorderLeft(BorderStyle.THIN);
            styleCellKLD.setBorderRight(BorderStyle.THIN);

            CellStyle styleCellCLD = workbook.createCellStyle();
            styleCellCLD.setAlignment(HorizontalAlignment.CENTER);
            styleCellCLD.setVerticalAlignment(VerticalAlignment.CENTER);
            styleCellCLD.setWrapText(true);
//            styleCellCLD.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
//            styleCellCLD.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            styleCellCLD.setBorderTop(BorderStyle.THIN);
            styleCellCLD.setBorderBottom(BorderStyle.THIN);
            styleCellCLD.setBorderLeft(BorderStyle.THIN);
            styleCellCLD.setBorderRight(BorderStyle.THIN);

            CellStyle styleCellQT = workbook.createCellStyle();
            styleCellQT.setAlignment(HorizontalAlignment.CENTER);
            styleCellQT.setVerticalAlignment(VerticalAlignment.CENTER);
            styleCellQT.setWrapText(true);
//            styleCellQT.setFillForegroundColor(IndexedColors.RED.getIndex());
//            styleCellQT.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            styleCellQT.setBorderTop(BorderStyle.THIN);
            styleCellQT.setBorderBottom(BorderStyle.THIN);
            styleCellQT.setBorderLeft(BorderStyle.THIN);
            styleCellQT.setBorderRight(BorderStyle.THIN);

            // Danh sách tên các phòng họp lấy từ (HC_CASE_PHONGHOP_REPORT_VIEW)
            if (!H.isTrue(hcMasterCaseExcelReportResultList)) return;
//            dayMeetingMap = hcMasterCaseExcelReportResultList.stream()
//                    .collect(Collectors.groupingBy(
//                            HcCaseMeeting::getStartTime,
//                            LinkedHashMap::new,
//                            Collectors.groupingBy(HcCaseMeeting::getHcCasePhonghopId, Collectors.toList())
//                    ));
            Map<Date, Map<String, List<HcCaseMeeting>>> dayMeetingMap2 = hcMasterCaseExcelReportResultList.stream()
                    .collect(Collectors.groupingBy(
                            HcCaseMeeting::getStartTimeRound,
                            LinkedHashMap::new,
                            Collectors.groupingBy(HcCaseMeeting::getHcCasePhonghopId, Collectors.toList())
                    ));
            List<String> roomNameList = new ArrayList<>();
            Map<String, String> hcCasePhonghopIdToPhonghopNameList = new HashMap<>();
            for (HcCaseMeeting lichDat : hcMasterCaseExcelReportResultList) {
                String roomTitle = H.isTrue(lichDat.getRoomName()) ? lichDat.getRoomName().trim() : "";
                String hcCasePhonghopId = H.isTrue(lichDat.getHcCasePhonghopId()) ? lichDat.getHcCasePhonghopId().trim() : "";
                if (!roomNameList.contains(roomTitle)) {
                    roomNameList.add(roomTitle);
                }
                if (!hcCasePhonghopIdToPhonghopNameList.containsKey(hcCasePhonghopId)) {
                    hcCasePhonghopIdToPhonghopNameList.put(hcCasePhonghopId, roomTitle);
                }
            }

            Map<String, Integer> phongHopToColMap = new HashMap<>();
            // Tạo tiêu đề cột
            String[] headerArr = new String[roomNameList.size() + 2];
            headerArr[0] = "";
            headerArr[1] = "Buổi";
            for (int i = 0; i < roomNameList.size(); i++) {
                headerArr[i + 2] = roomNameList.get(i);
                for (Map.Entry<String, String> ele : hcCasePhonghopIdToPhonghopNameList.entrySet()) {
                    if (ele.getValue().equalsIgnoreCase(roomNameList.get(i))) {
                        phongHopToColMap.put(ele.getKey(), i + 2);
                    }
                }
            }

            Row headerRow = sheet.createRow(0);
            headerRow.setHeightInPoints(30);

            for (int i = 0; i < headerArr.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(headerArr[i]);
                sheet.setColumnWidth(i, 256 * 25);
                if (!cell.getStringCellValue().isEmpty()) {
                    // Áp dụng style tô nền cho tên các cột tiêu đề
                    cell.setCellStyle(styleRowHeader);
                }
            }

            int rowIndex = 1; // hàng thứ 2 sau hàng tên tiêu đề

            for (Map.Entry<Date, Map<String, List<HcCaseMeeting>>> dayMeeting : dayMeetingMap2.entrySet()) {
                LocalDateTime startMeetingTime = dayMeeting.getKey().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
                Map<String, List<HcCaseMeeting>> hcCaseMeetingMap = dayMeeting.getValue();
                String meetingWeekdayFormatter = setFormattedWeekday(startMeetingTime);

                // tạo 2 map chứa các cuộc họp buổi sáng và buổi chiều
                Map<String, List<HcCaseMeeting>> hcCaseMeetingMorningList = new HashMap<>();
                Map<String, List<HcCaseMeeting>> hcCaseMeetingAfternoonList = new HashMap<>();
                for(Map.Entry<String, List<HcCaseMeeting>> meeting : hcCaseMeetingMap.entrySet()) {
                    LocalDateTime startTime = meeting.getValue().get(0).getStartTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();

                    if (isMorningTime2(startTime)) {
                        hcCaseMeetingMorningList.put(meeting.getKey(), meeting.getValue());
                    } else hcCaseMeetingAfternoonList.put(meeting.getKey(), meeting.getValue());
                }

                Row dataRow = sheet.createRow(rowIndex);
                if(hcCaseMeetingMorningList.size() > 0 && hcCaseMeetingAfternoonList.size() > 0) {
                    sheet.addMergedRegion(new CellRangeAddress(rowIndex,rowIndex + 1,0,0));
                }
                Cell cellA = dataRow.createCell(0);
                cellA.setCellValue(meetingWeekdayFormatter);
                cellA.setCellStyle(styleColumA);


                // Phân loại các buổi họp vào cùng 1 list sáng hoặc chiều
                if(hcCaseMeetingMorningList.size() > 0) {
                    Cell cellB = dataRow.createCell(1);
                    cellB.setCellStyle(styleColumBS);

                    cellB.setCellValue("Sáng");
                    for(int j = 2; j < headerArr.length; j++) {
                        if(dataRow.getCell(j) != null) {
                            dataRow.getCell(j).setCellStyle(styleRowData);
                        }
                        else dataRow.createCell(j).setCellStyle(styleRowData);
//                        dataRow.getCell(j).setCellValue("");
                    }
                    fillDataExcel(hcCaseMeetingMorningList, phongHopToColMap, dataRow, styleRowData, styleCellCLD, styleCellKLD, styleCellQT, "Sáng", workbook, headerArr);

                    rowIndex++;
                }
                if(hcCaseMeetingAfternoonList.size() > 0) {

                    if(sheet.getRow(rowIndex) == null ) {
                        dataRow = sheet.createRow(rowIndex);
                    }
                    Cell cellB = dataRow.createCell(1);
                    cellB.setCellStyle(styleColumBS);

                    cellB.setCellValue("Chiều");
                    for(int j = 2; j < headerArr.length; j++) {
                        if(dataRow.getCell(j) != null) {
                            dataRow.getCell(j).setCellStyle(styleRowData);
                        }
                        else dataRow.createCell(j).setCellStyle(styleRowData);
//                        dataRow.getCell(j).setCellValue("");
                    }
                    fillDataExcel(hcCaseMeetingAfternoonList, phongHopToColMap, dataRow, styleRowData, styleCellCLD, styleCellKLD, styleCellQT, "Chiều", workbook, headerArr);

                    rowIndex++;
                }

            }

            // Ghi vào file
            FileOutputStream outputStream = new FileOutputStream("LichDatPhongHop2.xlsx");
            workbook.write(outputStream);
            workbook.close();
            outputStream.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static void fillDataExcel(Map<String, List<HcCaseMeeting>> hcCaseMeetingMorningMap, Map<String, Integer> phongHopToColMap, Row dataRow, CellStyle styleColumA, CellStyle styleCellCLD, CellStyle styleCellKLD, CellStyle styleCellQT, String type, Workbook workbook, String[] headerArr) {
        for (Map.Entry<String, List<HcCaseMeeting>> hcCasePhongHopIdMeeting : hcCaseMeetingMorningMap.entrySet()) {
            String hcCasePhongHopId = hcCasePhongHopIdMeeting.getKey();
            List<HcCaseMeeting> meetings = hcCasePhongHopIdMeeting.getValue();

            // Kiểm tra xem phòng họp của cuộc họp hiện tại có phải là phòng họp hiện đang xét không
            if (phongHopToColMap.containsKey(hcCasePhongHopId)) {
                // Tạo một hàng mới cho mỗi ngày
                dataRow.setHeight((short) -1);

//                }
//               for(int i = 2; i < headerArr.length; i++) {
//                   dataRow.createCell(i).setCellStyle(styleColumA);
//
//               }

                DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

                String startTime = null;
                String endTime = null;
                String services = null;

                for (HcCaseMeeting hcCaseMeeting : meetings) {
                    if (H.isTrue(hcCaseMeeting.getStartTime()) && H.isTrue(hcCaseMeeting.getEndTime())) {
                        startTime = hcCaseMeeting.getStartTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime().format(timeFormatter);
                        endTime = hcCaseMeeting.getEndTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime().format(timeFormatter);

                    }

                    if (H.isTrue(hcCaseMeeting.getServices())) {
                        services = hcCaseMeeting.getServices();
                    }
                }

                StringBuilder result = new StringBuilder();

                if (!startTime.isEmpty() && !endTime.isEmpty()) {
                    result.append(startTime).append("-").append(endTime).append("\n");
                }

                for (HcCaseMeeting hcCaseMeeting : meetings) {
                    if (H.isTrue(hcCaseMeeting.getMeetingMemberFullname())) {
                        result.append(hcCaseMeeting.getMeetingMemberFullname()).append(", ");
                    }
                }
                result.delete(result.length() - 2, result.length());
                result.append("\n");

                if (H.isTrue(services)) {
                    result.append(services);
                }

                // Logic xử lý cuộc họp và điền dữ liệu vào ô cell

                Cell cells = dataRow.getCell(phongHopToColMap.get(hcCasePhongHopId));

                XSSFRichTextString all;
                XSSFRichTextString richString = new XSSFRichTextString(result.toString());
                if(cells == null) {
                    cells = dataRow.createCell(phongHopToColMap.get(hcCasePhongHopId)); // Bắt đầu từ cột thứ 2
                    all = new XSSFRichTextString();
                }
                else {
                    all = (XSSFRichTextString) cells.getRichStringCellValue();
                    result.insert(0, "\n");
                }
                Font blueFont = workbook.createFont();
                blueFont.setBold(true);
                blueFont.setColor(IndexedColors.BLUE.getIndex());

                Font redFont = workbook.createFont();
                redFont.setColor(IndexedColors.RED.getIndex());

                Font yellowFont = workbook.createFont();
                yellowFont.setBold(true);
                yellowFont.setColor(IndexedColors.DARK_YELLOW.getIndex());
                // Thêm kiểm tra criticalLevel và setCellStyle tương ứng
                HcCaseMeeting hcCaseMeeting = meetings.get(0);

                    if (H.isTrue(hcCaseMeeting.getCriticalLevel())) {
                        switch (hcCaseMeeting.getCriticalLevel().toUpperCase()) {
                            case "COLANHDAO":
                                cells.setCellStyle(styleCellCLD);

                                richString.applyFont(yellowFont);
                                all.append(result.toString(), (XSSFFont) yellowFont);
                                break;
                            case "KHONGLANHDAO":
                                cells.setCellStyle(styleCellKLD);
                                richString.applyFont(blueFont);
                                all.append(result.toString(), (XSSFFont) blueFont);
                                break;
                            case "QUANTRONG":
                                cells.setCellStyle(styleCellQT);
                                richString.applyFont(redFont);
                                all.append(result.toString(), (XSSFFont) redFont);
                                break;
                        }
                    }
                cells.setCellValue(all);
            }
        }
    }

    public static String getFormattedWeekday(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int weekday = cal.get(Calendar.DAY_OF_WEEK);
        String str = DateUtils.of(date).format("dd/MM");
        String formatWeekday = weekday == 1 ? "Chủ nhật" : ("Thứ " + weekday);

        String rs = formatWeekday + "\n" + "(" + str + ")";
        return rs;
    }

    public static String setFormattedWeekday (LocalDateTime localDateTime) {
        String dateFormatted = localDateTime.format(DateTimeFormatter.ofPattern("dd/MM"));
        int weekdayInt = localDateTime.getDayOfWeek().getValue();
        String weekdayStr = 1 == weekdayInt ? "Chủ Nhật" : ("Thứ " + weekdayInt);
        return weekdayStr + "\n" + dateFormatted;
    }

    public static boolean isMorningTime(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        if (hour >= 0  && hour < 12) return true;
        else return false;
    }


    public static boolean isMorningTime2(LocalDateTime localDateTime) {
        int ampm = localDateTime.get(ChronoField.AMPM_OF_DAY);
        if (ampm == 0) {
            return true;
        }
        return false;
    }

    public static String removePrefix(String input) {
        String prefix = "[Phòng họp] (Lịch tuần)";
        int index = input.indexOf(prefix);
        if (index != -1) {
            return input.substring(index + prefix.length()).trim();
        }
        return input;
    }

    // Phương thức hỗ trợ để lấy giá trị của ô dưới dạng chuỗi
    private static String getCellValueAsString(Cell cell) {
        if (cell == null) {
            return "";
        }

        CellType cellType = cell.getCellType();

        if (cellType == CellType.STRING) {
            return cell.getStringCellValue();
        } else if (cellType == CellType.NUMERIC) {
            return String.valueOf(cell.getNumericCellValue());
        } else if (cellType == CellType.BOOLEAN) {
            return String.valueOf(cell.getBooleanCellValue());
        } else if (cellType == CellType.FORMULA) {
            return cell.getCellFormula();
        } else {
            return "";
        }
    }

    // Phương thức hỗ trợ để merge các ô trong một cột
    private static void mergeCellsInColumnA(Sheet sheet, Map<String, CellRangeAddress> mergeMap) {
        for (CellRangeAddress cellRangeA : mergeMap.values()) {
            // Merge các ô trong cột dựa trên thông tin trong mergeMap
            if (cellRangeA.getNumberOfCells() > 1) sheet.addMergedRegion(cellRangeA);
        }
    }

    // Phương thức hỗ trợ để merge các ô trong một cột
    private static void mergeCellsInColumnB(Sheet sheet, Map<String, CellRangeAddress> mergeMap) {
        for (CellRangeAddress cellRangeB : mergeMap.values()) {
            if (cellRangeB.getNumberOfCells() > 1) sheet.addMergedRegion(cellRangeB);
        }
    }

}
