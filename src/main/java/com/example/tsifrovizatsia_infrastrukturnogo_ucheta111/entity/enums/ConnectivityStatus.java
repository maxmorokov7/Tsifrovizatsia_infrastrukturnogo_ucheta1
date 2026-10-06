package com.example.tsifrovizatsia_infrastrukturnogo_ucheta111.entity.enums;


/** Статус заявки на связность */
public enum ConnectivityStatus {
    /** Черновик — создан, но заявка не отправлена */
    NEW,
    /** Заявка отправлена, но ещё не выполнена */
    REQUESTED,
    /** Доступ предоставлен и проверен */
    ACTIVE,
    /** Заявку выполнили, но доступ не работает */
    REWORK,
    /** Отозван или прекратил действие */
    REVOKED
}