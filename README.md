### Описание проекта

Автоматизация тестирования формы на сайте https://practice-automation.com/form-fields/

Технологии: Java, Selenium WebDriver, TestNG, Maven, Allure

Запуск тестов: mvn clean test

Генерация Allure отчета: mvn allure:report

Просмотр отчета: mvn allure:serve (через браузер)

Скрин полученного отчета: allure_report.png


### Тест-кейсы

**TC-1: Позитивный тест - Успешное заполнение формы**

**Предусловие:**
1. Открыть браузер
2. Перейти по ссылке https://practice-automation.com/form-fields/

**Шаги:**
1. Заполнить поле Name значением "Иван Петров"
2. Заполнить поле Password значением "SecurePass123!"
3. Из списка What is your favorite drink? выбрать Milk и Coffee
4. Из списка What is your favorite color? выбрать Yellow
5. В поле Do you like automation? выбрать вариант "Yes"
6. Поле Email заполнить строкой "ivan@example.com"
7. В поле Message написать количество инструментов, описанных в пункте Automation tools, и написать инструмент из списка Automation tools, содержащий наибольшее количество символов (например, "5 Katalon Studio")
8. Нажать на кнопку Submit

**Ожидаемый результат:** появился алерт с текстом Message received!

**TC-2: Негативный тест - Отправка формы с пустым обязательным полем Name**

**Предусловие:**
1. Открыть браузер
2. Перейти по ссылке https://practice-automation.com/form-fields/

**Шаги:**
1. Заполнить поле Name оставить пустым
2. Заполнить поле Password значением "SecurePass123!"
3. Из списка What is your favorite drink? выбрать Milk и Coffee
4. Из списка What is your favorite color? выбрать Yellow
5. В поле Do you like automation? выбрать вариант "Yes"
6. Поле Email заполнить строкой "ivan@example.com"
7. В поле Message написать количество инструментов, описанных в пункте Automation tools, и написать инструмент из списка Automation tools, содержащий наибольшее количество символов (например, "5 Katalon Studio")
8. Нажать на кнопку Submit

**Ожидаемый результат:** форма не отправляется, браузер показывает сообщение об ошибке валидации "Please fill out this field"