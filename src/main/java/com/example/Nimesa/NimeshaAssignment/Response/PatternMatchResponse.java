package com.example.Nimesa.NimeshaAssignment.Response;

import com.example.Nimesa.NimeshaAssignment.Dto.PatternMatchResponseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class PatternMatchResponse extends BaseResponse{
    private List<PatternMatchResponseDto> data;
}
