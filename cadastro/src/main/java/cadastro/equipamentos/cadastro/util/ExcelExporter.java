package cadastro.equipamentos.cadastro.util;

import cadastro.equipamentos.cadastro.model.Equipamento;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ExcelExporter {
    private XSSFWorkbook workbook;
    private Sheet sheet;

    public ExcelExporter() {
        workbook = new XSSFWorkbook();
    }

    private void writeHeaderLine() {
        sheet = workbook.createSheet("Equipamentos");
        Row row = sheet.createRow(0);

        // Estilo para o cabeçalho
        CellStyle headerStyle = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setBold(true);
        font.setColor(IndexedColors.WHITE.getIndex());
        headerStyle.setFont(font);
        headerStyle.setFillForegroundColor(IndexedColors.DARK_BLUE.getIndex());
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        headerStyle.setBorderBottom(BorderStyle.THICK);
        headerStyle.setBorderTop(BorderStyle.THICK);
        headerStyle.setBorderRight(BorderStyle.THICK);
        headerStyle.setBorderLeft(BorderStyle.THICK);

        createCell(row, 0, "ID", headerStyle);
        createCell(row, 1, "Número de Série", headerStyle);
        createCell(row, 2, "Marca", headerStyle);
        createCell(row, 3, "Modelo", headerStyle);
        createCell(row, 4, "Data de Entrega", headerStyle);
    }

    private void createCell(Row row, int columnCount, Object value, CellStyle style) {
        Cell cell = row.createCell(columnCount);

        if (value instanceof Integer) {
            cell.setCellValue((Integer) value);
        } else if (value instanceof Long) {
            cell.setCellValue((Long) value);
        } else {
            cell.setCellValue(value == null ? "" : value.toString());
        }

        cell.setCellStyle(style);
    }

    private void writeDataLines(List<Equipamento> equipamentos) {
        int rowCount = 1;

        // Estilo para dados
        CellStyle dataStyle = workbook.createCellStyle();
        dataStyle.setBorderBottom(BorderStyle.THIN);
        dataStyle.setBorderTop(BorderStyle.THIN);
        dataStyle.setBorderRight(BorderStyle.THIN);
        dataStyle.setBorderLeft(BorderStyle.THIN);

        // Estilo para datas
        CellStyle dateStyle = workbook.createCellStyle();
        dateStyle.cloneStyleFrom(dataStyle);
        CreationHelper createHelper = workbook.getCreationHelper();
        dateStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd/MM/yyyy"));

        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        for (Equipamento equipamento : equipamentos) {
            Row row = sheet.createRow(rowCount++);
            int col = 0;

            createCell(row, col++, equipamento.getId(), dataStyle);
            createCell(row, col++, equipamento.getNumeroSerie(), dataStyle);
            createCell(row, col++, equipamento.getMarca(), dataStyle);
            createCell(row, col++, equipamento.getModelo(), dataStyle);
            createCell(row, col++, equipamento.getDataEntrega() == null ? "" : equipamento.getDataEntrega().format(fmt), dateStyle);
        }

        // Auto-ajustar largura das colunas
        for (int i = 0; i < 5; i++) {
            sheet.autoSizeColumn(i);
        }
    }

    public void export(List<Equipamento> equipamentos, HttpServletResponse response) throws IOException {
        writeHeaderLine();
        writeDataLines(equipamentos);

        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=equipamentos.xlsx");

        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();
    }
}