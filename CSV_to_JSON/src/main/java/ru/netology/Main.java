package ru.netology;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.opencsv.CSVReader;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        // CSV to data.json
        String[] columnMapping = {"id", "firstName", "lastName", "country", "age"};
        String csvFile = "data.csv";

        List<Employee> csvList = parseCSV(columnMapping, csvFile);
        if (csvList == null) {
            System.err.println("Ошибка при парсинге CSV. Проверьте " + csvFile);
        } else {
            String jsonFromCsv = listToJson(csvList);
            if (writeString(jsonFromCsv, "data.json")) {
                System.out.println("data.json записан успешно");
            } else {
                System.err.println("Ошибка записи data.json");
            }
        }

        // XML to data2.json
        String xmlFile = "data.xml";
        List<Employee> xmlList = parseXML(xmlFile);
        if (xmlList == null) {
            System.err.println("Ошибка при парсинге XML. Проверьте " + xmlFile);
        } else {
            String jsonFromXml = listToJson(xmlList);
            if (writeString(jsonFromXml, "data2.json")) {
                System.out.println("data2.json записан успешно");
            } else {
                System.err.println("Ошибка записи data2.json");
            }
        }
    }

    // Парсинг CSV в List<Employee>
    public static List<Employee> parseCSV(String[] columnMapping, String fileName) {
        ColumnPositionMappingStrategy<Employee> strategy = new ColumnPositionMappingStrategy<>();
        strategy.setType(Employee.class);
        strategy.setColumnMapping(columnMapping);

        try (CSVReader csvReader = new CSVReader(new FileReader(fileName))) {
            CsvToBean<Employee> csvToBean = new CsvToBeanBuilder<Employee>(csvReader)
                    .withMappingStrategy(strategy)
                    .withSkipLines(0)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            return csvToBean.parse();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Парсинг XML в List<Employee>
    public static List<Employee> parseXML(String fileName) {
        List<Employee> employees = new ArrayList<>();
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(fileName);
            doc.getDocumentElement().normalize();

            // Получаем корневой элемент
            Element root = doc.getDocumentElement(); // <staff>
            NodeList children = root.getChildNodes();

            for (int i = 0; i < children.getLength(); i++) {
                Node node = children.item(i);
                // Отфильтруем текстовые / пустые ноды
                if (node.getNodeType() != Node.ELEMENT_NODE) {
                    continue;
                }
                Element employeeElement = (Element) node;
                if (!"employee".equals(employeeElement.getTagName())) {
                    continue;
                }

                String idText = getElementTextContent(employeeElement, "id");
                String firstName = getElementTextContent(employeeElement, "firstName");
                String lastName = getElementTextContent(employeeElement, "lastName");
                String country = getElementTextContent(employeeElement, "country");
                String ageText = getElementTextContent(employeeElement, "age");

                long id = 0;
                int age = 0;
                try {
                    id = Long.parseLong(idText);
                } catch (NumberFormatException nfe) {
                    System.err.println("Неверный id в XML: " + idText + " (будет 0)");
                }
                try {
                    age = Integer.parseInt(ageText);
                } catch (NumberFormatException nfe) {
                    System.err.println("Неверный age в XML: " + ageText + " (будет 0)");
                }

                Employee emp = new Employee(id, firstName, lastName, country, age);
                employees.add(emp);
            }

            return employees;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static String getElementTextContent(Element parent, String tagName) {
        NodeList nl = parent.getElementsByTagName(tagName);
        if (nl == null || nl.getLength() == 0) {
            return "";
        }
        Node n = nl.item(0);
        if (n == null) return "";
        return n.getTextContent().trim();
    }

    public static <T> String listToJson(List<T> list) {
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();

        Type listType = new TypeToken<List<T>>() {}.getType();
        return gson.toJson(list, listType);
    }

    public static boolean writeString(String json, String fileName) {
        try (FileWriter file = new FileWriter(fileName)) {
            file.write(json);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
