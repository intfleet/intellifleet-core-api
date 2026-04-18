package com.intellifleet.dto;

import jakarta.persistence.Column;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Data
public class QueryDTO {
    private Long id;
    private String name;
    private String label;
    private String type;
    private String note;
    private String activeRow;
    private List<FormConstraintDTO> relations;
    private String sql;

    public static void main(String args[]) {
        String input = "Swiss";
        List<String> chars = Arrays.asList(input.split(""));
//        List<String> nonRepeatedChars = chars.stream().collect(Collectors.groupingBy(Functions.identify(),Collectors.counting())).entrySet().stream().filter(entry-> entry.getValue() == 1).map(Map.Entry::getKey).collect(Collectors.toList());
//        System.out.println(nonRepeatedChars);
    }
}
