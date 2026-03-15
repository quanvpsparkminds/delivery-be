package net.sparkminds.delivery.enums;

public enum EOrderStatus {
    PENDING,        // tìm kiếm tài xế
    CONFIRMED,      // Tài xế nhận đơn
    PREPARING,      // Đang chuẩn bị món
    READY,          // Đã sẵn sàng giao
    DELIVERING,     // Đang giao hàng
    COMPLETED,      // Đã giao thành công
    CANCELLED,      // Đã hủy
    FAILED          // Giao thất bại
}
