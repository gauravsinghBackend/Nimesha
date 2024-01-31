package com.example.Nimesa.NimeshaAssignment.Response;

import com.example.Nimesa.Util.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class BaseResponse {
    private String message;
    private Status status;
}
