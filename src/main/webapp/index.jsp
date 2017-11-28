
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
    <body>
        <!-- Форма, содержащая в себе кнопку, 2 техтовых поля, 3 переключателя (radiobutton).
             По нажатию на кнопку отправит POST запрос на сервлет-->
        <form action="servlet" method="POST">
            <div>
                <h1> ЗА ЖИЗНЬ ГУМАННУЮ, ЗА КАШУ МАННУЮ...
                    <p>
                        ВОТ ТЕБЕ КАЛЬКУЛЯТОР РАСЧЕТА НАЛОГА НА ИМУЩЕСТВО
                    </p>
                </h1>
            </div>
            <h2> Введите кадастровую стоимость</h2>
            <p><input type="text" name="kad_stoim" value="" placeholder="Кадастровая стоимость = "> млн. руб.</p>
            <h2> Выберите тип собственности </h2>
            <p>
            <input type="radio" name="tip" checked="checked"  value="20" > - Квартира
            <input type="radio" name="tip" value="10"> - Комната
            <input type="radio" name="tip" value="50"> - Дом (жилой)
            </p>
            <h2> Введите площадь помещения</h2>
            <p><input type="text" name="S" value="" placeholder="Введите площадь"> м2</p>
            <input type="submit" value="РАССЧИТАТЬ!!!" />
        </form>
    </body>
</html>


