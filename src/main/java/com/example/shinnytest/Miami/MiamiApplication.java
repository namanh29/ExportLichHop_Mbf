package com.example.shinnytest.Miami;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.WorkbookUtil;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
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
        hcMasterCaseExcelReportResultList = readExcelFile("EOFFICE_HC_CASE_PHONGHOP_REPORT_VIEW_2.xlsx");
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
            excelData.setRoomDesc(row.getCell(20).getStringCellValue());
            excelData.setCriticalLevel(row.getCell(21).getStringCellValue());

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
            styleCellKLD.setFillForegroundColor(IndexedColors.AQUA.getIndex());
            styleCellKLD.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            styleCellKLD.setBorderTop(BorderStyle.THIN);
            styleCellKLD.setBorderBottom(BorderStyle.THIN);
            styleCellKLD.setBorderLeft(BorderStyle.THIN);
            styleCellKLD.setBorderRight(BorderStyle.THIN);

            CellStyle styleCellCLD = workbook.createCellStyle();
            styleCellCLD.setAlignment(HorizontalAlignment.CENTER);
            styleCellCLD.setVerticalAlignment(VerticalAlignment.CENTER);
            styleCellCLD.setWrapText(true);
            styleCellCLD.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
            styleCellCLD.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            styleCellCLD.setBorderTop(BorderStyle.THIN);
            styleCellCLD.setBorderBottom(BorderStyle.THIN);
            styleCellCLD.setBorderLeft(BorderStyle.THIN);
            styleCellCLD.setBorderRight(BorderStyle.THIN);

            CellStyle styleCellQT = workbook.createCellStyle();
            styleCellQT.setAlignment(HorizontalAlignment.CENTER);
            styleCellQT.setVerticalAlignment(VerticalAlignment.CENTER);
            styleCellQT.setWrapText(true);
            styleCellQT.setFillForegroundColor(IndexedColors.RED.getIndex());
            styleCellQT.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            styleCellQT.setBorderTop(BorderStyle.THIN);
            styleCellQT.setBorderBottom(BorderStyle.THIN);
            styleCellQT.setBorderLeft(BorderStyle.THIN);
            styleCellQT.setBorderRight(BorderStyle.THIN);

            // Danh sách tên các phòng họp lấy từ (HC_CASE_PHONGHOP_REPORT_VIEW)
            if (!H.isTrue(hcMasterCaseExcelReportResultList)) return;
            dayMeetingMap = hcMasterCaseExcelReportResultList.stream()
                    .collect(Collectors.groupingBy(
                            HcCaseMeeting::getStartTime,
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

            // Tạo format DateTime và chuyển từ ngày đến ngày thành dạng LocalDate
            int rowIndex = 1; // hàng thứ 2 sau hàng tên tiêu đề
            LocalDateTime fromLocalDateTimeInput = Instant.ofEpochMilli(fromTimeInput)
                    .atZone(ZoneId.systemDefault())
                    .toLocalDateTime();
            LocalDateTime endLocalDateTimeInput = Instant.ofEpochMilli(endTimeInput)
                    .atZone(ZoneId.systemDefault())
                    .toLocalDateTime();

            List<Map<String, List<HcCaseMeeting>>> hcCaseMeetingMorningList = new ArrayList<>();
            List<Map<String, List<HcCaseMeeting>>> hcCaseMeetingAfternoonList = new ArrayList<>();
            int previousDay = 0;
            for (Map.Entry<Date, Map<String, List<HcCaseMeeting>>> dayMeeting : dayMeetingMap.entrySet()) {
                LocalDateTime startMeetingTime = dayMeeting.getKey().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
                Map<String, List<HcCaseMeeting>> hcCaseMeetingMap = dayMeeting.getValue();
                String meetingWeekdayFormatter = setFormattedWeekday(startMeetingTime);

                int isDay = startMeetingTime.getDayOfMonth();

                int rowIndexForDay = rowIndex; // Sử dụng để theo dõi hàng cho mỗi ngày
                int rowIndexForMeeting = rowIndexForDay; // Sử dụng để theo dõi hàng cho mỗi cuộc họp trong 1 ngày
                Row dataRow = sheet.createRow(rowIndex);

                // Phân loại các buổi họp vào cùng 1 list sáng hoặc chiều
                if (isMorningTime2(startMeetingTime)) {
                    hcCaseMeetingMorningList.add(hcCaseMeetingMap);
                } else hcCaseMeetingAfternoonList.add(hcCaseMeetingMap);

                for (Map<String, List<HcCaseMeeting>> hcCaseMeetingMorningMap : hcCaseMeetingMorningList) {
                    fillDataExcel(hcCaseMeetingMorningMap, phongHopToColMap, dataRow, rowIndexForMeeting,
                            rowIndexForDay, meetingWeekdayFormatter, styleColumA, startMeetingTime, styleColumBS,
                            styleColumBC, styleCellCLD, styleCellKLD, styleCellQT);
                }

                for (Map<String, List<HcCaseMeeting>> hcCaseMeetingAfternoonMap : hcCaseMeetingAfternoonList) {
                    fillDataExcel(hcCaseMeetingAfternoonMap, phongHopToColMap, dataRow, rowIndexForMeeting,
                            rowIndexForDay, meetingWeekdayFormatter, styleColumA, startMeetingTime, styleColumBS,
                            styleColumBC, styleCellCLD, styleCellKLD, styleCellQT);
                }

//                    rowIndex = rowIndexForMeeting; // Cập nhật rowIndex cho ngày tiếp theo
                if (previousDay < isDay) {
                    previousDay = isDay;
                    rowIndex++; // Cập nhật rowIndex cho ngày tiếp theo
                }

            }
//            Map<String, CellRangeAddress> columnDataRanges = new HashMap<>();

            // Quá trình siêu merge các ô excel 16-10-2023
            int rowMergeStartOfColA = 1;
            int rowMergeEndOfColA = 1;
            Map<String, CellRangeAddress> mergeColumnMapA = new HashMap<>();
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row rowA = sheet.getRow(i);
                String cellValueOfColA = getCellValueAsString(rowA.getCell(0));

                // Merge các ô giống nhau trong cột đầu tiên
                if (!cellValueOfColA.isEmpty()) {
                    if (mergeColumnMapA.containsKey(cellValueOfColA)) {
                        rowMergeEndOfColA++;
                    } else {
                        rowMergeStartOfColA = rowMergeEndOfColA = rowA.getRowNum();
                    }
                    mergeColumnMapA.put(cellValueOfColA, new CellRangeAddress(rowMergeStartOfColA, rowMergeEndOfColA,
                            0, 0));
                }
            }

            // phần merge cột thứ 2 buổi họp đang thực hiện
            /*for (Map.Entry<String, CellRangeAddress> s : mergeColumnMapA.entrySet()) {
                CellRangeAddress cellRangeAddressB = s.getValue().copy();
                cellRangeAddressB.setFirstColumn(1);
                cellRangeAddressB.setLastColumn(1);

                int startRowMergeOfColB = 1;
                int endRowMergeOfColB = 1;
                int rowStartB = cellRangeAddressB.getFirstRow();
                int rowEndB = cellRangeAddressB.getLastRow();
                Map<String, CellRangeAddress> mergeColumnMapB = new HashMap<>();;
                for (int i = rowStartB; i <= rowEndB; i++) {

                    Row rowB = sheet.getRow(i);
                    String cellValueOfColB = getCellValueAsString(rowB.getCell(1));

                    // Merge các ô giống nhau trong cột thứ 2
                    if (H.isTrue(cellValueOfColB)) {
                        if (mergeColumnMapB.containsKey(cellValueOfColB)) {
                            endRowMergeOfColB = rowB.getRowNum();
                        } else {
                            startRowMergeOfColB = endRowMergeOfColB = rowStartB;
                        }
                        mergeColumnMapB.put(cellValueOfColB, new CellRangeAddress(startRowMergeOfColB, endRowMergeOfColB,
                                1, 1));
                    }

                }
                // Merge các ô giống nhau trong cột thứ hai
                mergeCellsInColumnB(sheet, mergeColumnMapB);
            }*/

             // Merge các ô giống nhau trong cột đầu tiên
            mergeCellsInColumnA(sheet, mergeColumnMapA);


            // Ghi vào file
            FileOutputStream outputStream = new FileOutputStream("LichDatPhongHop2.xlsx");
            workbook.write(outputStream);
            workbook.close();
            outputStream.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static void fillDataExcel(Map<String, List<HcCaseMeeting>> hcCaseMeetingMorningMap, Map<String, Integer> phongHopToColMap, Row dataRow, int rowIndexForMeeting, int rowIndexForDay, String meetingWeekdayFormatter, CellStyle styleColumA, LocalDateTime startMeetingTime, CellStyle styleColumBS, CellStyle styleColumBC, CellStyle styleCellCLD, CellStyle styleCellKLD, CellStyle styleCellQT) {
        for (Map.Entry<String, List<HcCaseMeeting>> hcCasePhongHopIdMeeting : hcCaseMeetingMorningMap.entrySet()) {
            String hcCasePhongHopId = hcCasePhongHopIdMeeting.getKey();
            List<HcCaseMeeting> meetings = hcCasePhongHopIdMeeting.getValue();

            // Kiểm tra xem phòng họp của cuộc họp hiện tại có phải là phòng họp hiện đang xét không
            if (phongHopToColMap.containsKey(hcCasePhongHopId)) {
                // Tạo một hàng mới cho mỗi ngày
                dataRow.setHeight((short) -1);

                // Các ô cho thứ và ngày (chỉ tạo 1 lần cho mỗi ngày)
                if (rowIndexForMeeting == rowIndexForDay) {
                    Cell cellA = dataRow.createCell(0);
                    cellA.setCellValue(meetingWeekdayFormatter);
                    cellA.setCellStyle(styleColumA);

                    Cell cellB = dataRow.createCell(1);
                    if (isMorningTime2(startMeetingTime)) {
                        cellB.setCellValue("Sáng");
                        cellB.setCellStyle(styleColumBS);
                    } else {
                        cellB.setCellValue("Chiều");
                        cellB.setCellStyle(styleColumBC);
                    }
                }

                // Logic xử lý cuộc họp và điền dữ liệu vào ô cell
                Cell cells = dataRow.createCell(phongHopToColMap.get(hcCasePhongHopId)); // Bắt đầu từ cột thứ 2
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
                result.append("\n");

                if (H.isTrue(services)) {
                    result.append(services);
                }

                if (result.length() > 0 && result.charAt(result.length() - 2) == ',') {
                    result.deleteCharAt(result.length() - 2);
                }

                cells.setCellValue(result.toString());

                // Thêm kiểm tra criticalLevel và setCellStyle tương ứng
                for (HcCaseMeeting hcCaseMeeting : meetings) {
                    if (H.isTrue(hcCaseMeeting.getCriticalLevel())) {
                        switch (hcCaseMeeting.getCriticalLevel().toUpperCase()) {
                            case "COLANHDAO":
                                cells.setCellStyle(styleCellCLD);
                                break;
                            case "KHONGLANHDAO":
                                cells.setCellStyle(styleCellKLD);
                                break;
                            case "QUANTRONG":
                                cells.setCellStyle(styleCellQT);
                                break;
                        }
                    }
                }
//                            rowIndexForMeeting++;
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
