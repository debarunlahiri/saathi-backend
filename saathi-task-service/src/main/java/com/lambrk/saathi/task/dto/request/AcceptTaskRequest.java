package com.lambrk.saathi.task.dto.request;

import jakarta.validation.constraints.NotNull;

public record AcceptTaskRequest(@NotNull Long partnerId) {}
