package validation;

import models.Task;

public class Validation {
    public static boolean emptyValues(String name, String description, String deadlineDate){
        return name.isEmpty() || description.isEmpty() || deadlineDate.isEmpty();
    }
}
