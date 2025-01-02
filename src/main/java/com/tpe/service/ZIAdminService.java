package com.tpe.service;

public interface ZIAdminService {
    // Admin girişi ve menüsüne erişim
    void adminMenu();

    // Kayıtlı adminin kontrol edilmesi ve dashboard işlemleri
    void registeredAdminDashboard();

    // Yeni admin kaydı oluşturulması
    void register();
}
