package com.example.Nimesa.NimeshaAssignment.Exception;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.example.Nimesa.Util.Status;
import com.example.Nimesa.Util.StringAll;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import software.amazon.awssdk.services.s3.model.NoSuchBucketException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AmazonS3Exception.class)
    public ResponseEntity<String> handleAmazonS3Exception(AmazonS3Exception e) {
        if ("IllegalLocationConstraintException".equals(e.getErrorCode())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Region is incompatible for the bucket.");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Amazon S3 error occurred: " + e.getMessage());
        }
    }

    @ExceptionHandler(NoSuchBucketException.class)
    public ResponseEntity<String> handleNoSuchBucketException(NoSuchBucketException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Bucket not found: " + e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred: " + e.getMessage());
    }
    @ExceptionHandler(S3BucketException.class)
    public ResponseEntity<GlobalResponse> s3BucketException(S3BucketException message){
        GlobalResponse globalResponse=GlobalResponse.builder()
                .message(message.getMessage())
                .status(Status.FAILURE)
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(globalResponse);
    }
}

