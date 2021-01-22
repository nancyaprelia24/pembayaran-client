package com.pembayaran.model;

public class Pembayaran {
		private int id;
		private int id_pembayaran;
		private int saldo;
		private String username;
		private int invoice_number;
		private int pin;
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public int getId_pembayaran() {
			return id_pembayaran;
		}
		public void setId_pembayaran(int id_pembayaran) {
			this.id_pembayaran = id_pembayaran;
		}
		public int getSaldo() {
			return saldo;
		}
		public void setSaldo(int saldo) {
			this.saldo = saldo;
		}
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		public int getInvoice_number() {
			return invoice_number;
		}
		public void setInvoice_number(int invoice_number) {
			this.invoice_number = invoice_number;
		}
		public int getPin() {
			return pin;
		}
		public void setPin(int pin) {
			this.pin = pin;
		}
		
		
	}

