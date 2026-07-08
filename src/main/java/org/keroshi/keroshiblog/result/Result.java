package org.keroshi.keroshiblog.result;

public record Result<T> (
	boolean success,
	String code,
	T data
) {
	public static <T> Result<T> ok() {
		return new Result<>(true, "SUCCESS", null);
	}

	public static <T> Result<T> ok(T data) {
		return new Result<>(true, "SUCCESS", data);
	}

	public static <T> Result<T> fail(String code) {
		return new Result<>(false, code, null);
	}

	public static <T> Result<T> fail(String code, T data) {
		return new Result<>(false, code, data);
	}
}
