package net.sparkminds.delivery.enums;

public enum EOrderStatus {
    PENDING,        // Chờ xác nhận
    CONFIRMED,      // Nhà hàng đã xác nhận
    PREPARING,      // Đang chuẩn bị món
    READY,          // Đã sẵn sàng giao
    DELIVERING,     // Đang giao hàng
    COMPLETED,      // Đã giao thành công
    CANCELLED,      // Đã hủy
    FAILED          // Giao thất bại
}
