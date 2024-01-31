package com.example.Nimesa.NimeshaAssignment.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class DiscoveryResultResponse extends  BaseResponse{
    private Object data;
}
