package com.example.Nimesa.NimeshaAssignment.Response;

import com.example.Nimesa.NimeshaAssignment.Dto.S3ObjectCountResponseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.modelmapper.internal.bytebuddy.implementation.bind.annotation.Super;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class S3ObjectCountResponse extends BaseResponse{
    private S3ObjectCountResponseDto data;
}
