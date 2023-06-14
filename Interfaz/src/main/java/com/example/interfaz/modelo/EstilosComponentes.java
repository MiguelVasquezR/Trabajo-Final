package com.example.interfaz.modelo;

public class EstilosComponentes {
        private String botones = "-fx-border-color: #fff; -fx-border-width: 2; -fx-border-radius: 30; -fx-background-radius: 30; -fx-background-color: rgba(2, 143, 200, .9); -fx-text-fill: #fff;";
        private String botonesOk = "-fx-border-color: #fff; -fx-border-width: 2; -fx-border-radius: 30; -fx-background-radius: 30; -fx-background-color: rgb(9, 211, 234); -fx-text-fill: #fff;";
        private String comboBox = "-fx-font-family: 'Georgia'; -fx-border-radius: 30; -fx-border-width: 2; -fx-border-color: rgba(0, 0,0, .7); -fx-background-radius: 30; -fx-background-color: transparent;";
        private String comboBoxE = "-fx-font-family: 'Georgia'; -fx-border-radius: 30; -fx-border-width: 2; -fx-border-color: red; -fx-background-radius: 30; -fx-background-color: transparent;";
        private String datePicker = "-fx-base: #1a99cd;-fx-text-fill: #fff; -fx-border-width: 2; -fx-border-color: #fff;";
        private String datePickerCampos = "-fx-base: #fff;-fx-background-color: transparent; -fx-text-fill: #000; -fx-border-width: 1; -fx-border-color: #000;";
        private String datePickerCamposE = "-fx-base: #fff;-fx-background-color: transparent; -fx-text-fill: #000; -fx-border-width: 1; -fx-border-color: red;";
        private String textfield = "-fx-border-width: 0 0 1 0; -fx-border-color: #000; -fx-text-fill: #000; -fx-prompt-text-fill: #aaa; -fx-background-color: transparent";
        private String textfieldSesion = "-fx-border-width: 0 0 1 0; -fx-border-color: #fff; -fx-text-fill: #fff; -fx-prompt-text-fill: #fff;";
        private String columnStyle = "-fx-font-family: 'Georgia'; -fx-font-size: 12pt; -fx-background-color: #f7f7f7; -fx-border-width: 0 1 1 0; -fx-border-color: #bbb;";
        private String textFieldError = "-fx-border-width: 0 0 1 0; -fx-border-color: red; -fx-text-fill: #fff; -fx-prompt-text-fill: #aaa; -fx-background-color: transparent";
        private String textFieldErrorC = "-fx-border-width: 0 0 1 0; -fx-border-color: red; -fx-text-fill: #000; -fx-prompt-text-fill: #aaa; -fx-background-color: transparent";

        public EstilosComponentes(){}
        public String getColumnStyle() {
            return columnStyle;
        }
        public String getBotones() {
            return botones;
        }
        public String getComboBox() {
            return comboBox;
        }
        public String getDatePicker() {
            return datePicker;
        }
        public String getTextfield() {
            return textfield;
        }
        public String getBotonesOk() {
            return botonesOk;
        }
        public String getDatePickerCampos() {
            return datePickerCampos;
        }
        public String getTextfieldSesion() {
            return textfieldSesion;
        }
        public String getTextFieldError() {
            return textFieldError;
        }
        public String getDatePickerCamposE() {
            return datePickerCamposE;
        }

        public String getComboBoxE() {
            return comboBoxE;
        }

        public String getTextFieldErrorC() {
            return textFieldErrorC;
        }
}
