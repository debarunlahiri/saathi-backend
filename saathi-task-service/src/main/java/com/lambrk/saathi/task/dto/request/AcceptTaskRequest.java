package com.lambrk.saathi.task.dto.request;

import jakarta.validation.constraints.NotNull;
import java.util.UUID;

public record AcceptTaskRequest(@NotNull UUID partnerId) {}
