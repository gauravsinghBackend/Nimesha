package com.example.Nimesa.NimeshaAssignment.Exception;

import com.example.Nimesa.Util.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GlobalResponse {
    private String message;
    private Status status;
}
