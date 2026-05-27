package auraevents.auraev.dto;

public record ApiResponse<T>(
        boolean sucesso,
        String mensagem,
        T dados
) {
    public static <T> ApiResponse<T> ok(String mensagem, T dados) { return new ApiResponse<>(true, mensagem, dados); }
    public static <T> ApiResponse<T> erro(String mensagem, T dados) { return new ApiResponse<>(false, mensagem, dados); }
}
