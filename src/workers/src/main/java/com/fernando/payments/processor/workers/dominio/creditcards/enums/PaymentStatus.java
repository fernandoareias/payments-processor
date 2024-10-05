package com.fernando.payments.processor.workers.dominio.creditcards.enums;

public enum PaymentStatus {
    /**
     * O pagamento foi recebido, mas ainda está aguardando processamento.
     */
    PENDING(0),

    /**
     * O pagamento está sendo processado.
     */
    PROCESSING(1),

    /**
     * O pagamento foi processado com sucesso.
     */
    COMPLETED(2),

    /**
     * O processamento do pagamento falhou.
     */
    FAILED(3),

    /**
     * O pagamento foi cancelado pelo usuário ou pelo sistema.
     */
    CANCELLED(4),

    /**
     * O pagamento foi reembolsado.
     */
    REFUNDED(5),

    /**
     * O pagamento expirou e não foi concluído.
     */
    EXPIRED(6),

    /**
     * O pagamento está em espera, geralmente aguardando a verificação de algum tipo.
     */
    ON_HOLD(7),

    /**
     * O pagamento está na fila para ser processado.
     */
    QUEUED(8);

    private final int code;

    PaymentStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
