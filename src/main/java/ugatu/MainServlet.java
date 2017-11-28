package ugatu;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MainServlet extends HttpServlet {
    /**
     * Основной метод класса HttpServlet, вызывается сервером для обработки GET запросов.
     *
     * @param req q{@link HttpServletRequest} объект, хранящий запрос,
     *                  сделанный клиентом к сервлету
     *
     * @param resp {@link HttpServletResponse} объект, хранящий ответ,
     *                  который сервлет отправляет на клиент
     *
     * @exception IOException вызывается, если обнаружена ошибка
     *                              ввода-вывода при обработке GET запроса
     *
     * @exception ServletException вызывается, если GET запрос
     *                                  не может быть обработан
     */

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req,resp); //Для пустого метода, передаем запрос дальше к классу родителю
    }

    /**
     * Основной метод класса HttpServlet, вызывается сервером для обработки POST запросов.
     *
     * @param req q{@link HttpServletRequest} объект, хранящий запрос,
     *                  сделанный клиентом к сервлету
     *
     * @param resp {@link HttpServletResponse} объект, хранящий ответ,
     *                  который сервлет отправляет на клиент
     *
     * @exception IOException вызывается, если обнаружена ошибка
     *                              ввода-вывода при обработке POST запроса
     *
     * @exception ServletException вызывается, если POST запрос
     *                                  не может быть обработан
     */

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Получение значения, переданного с клиента, в виде строк
        String kad_stoim = req.getParameter("kad_stoim");
        String tip = req.getParameter("tip");
        String S = req.getParameter("S");
        try {
            /*
            Переводим строки со значением в числа с плавающей точкой
            Если операция прошла успешно, то выполняем программу далее до окончания блока try{}
            Если в строке не было числел (например было буквы или было пусто), то произойдет ошибка
            и сразу же начнет выполняться блок catch{}
             */
            float kadastr_stoim = Float.parseFloat(kad_stoim);
            float tip_home = Float.parseFloat(tip);
            float area = Float.parseFloat(S);
            float answer = 0;
            float nal_base;
            float vichet;
            float koeff = 0;

            /*
            Если кадастровая стоимость (kadastr_stoim) указанная пользователем будет
            находиться в одном из пределов (i<10, 10<i<=20, 20<i<=50, 50<i<=300, i>300),
            то при расчете будет учитываться соответствующий значению коэффицент (koeff)
            */
            if (kadastr_stoim <=10)
            {
                koeff = (float) 0.1;
            }else
                if (kadastr_stoim>10 && kadastr_stoim<=20 )
            {
                koeff = (float) 0.15;
            }
            else
                if (kadastr_stoim>20 && kadastr_stoim<=50 )
                {
                    koeff = (float) 0.2;
                }
            else
                if (kadastr_stoim>50 && kadastr_stoim<=300 )
                {
                    koeff = (float) 0.3;
                }
            else
                if (kadastr_stoim>300 )
                {
                    koeff = 2;
                }
                
            /*
            Проверяем, если кадастровая стоимость менее 300,
            то расчет производятся следующим образом
             */
            if (kadastr_stoim<300) {
                vichet = (kadastr_stoim / area) * tip_home;
                nal_base = kadastr_stoim - vichet;
                answer = (nal_base * koeff) / 100;
            }
            else
                /*
                Иначе, если кадастровая стимость больше, либо равна 300,
                то расчет производятся следующим образом
                 */
                if (kadastr_stoim>=300)
                {
                    nal_base = kadastr_stoim;
                    answer = (nal_base * koeff) / 100;
                }

            /*
            Записываем в виде атрибута полученный результат
            атрибут имеет вид <ключ> <значение>
             */
            if (answer>=1) {
                req.setAttribute("answer", "Сумма налога составит = " + String.format("%.2f", answer) + " млн. руб.");
            }
            else
                if (answer<1) {
                    answer = answer*1000;
                    req.setAttribute("answer", "Сумма налога составит = " + String.format("%.2f", answer) + " тыс. руб.");
                }
        } catch (NumberFormatException exception) {
            /*
            Если произошла ошибка при переводе в численный тип,
            то в атрибут пишем сообщение с ошибкой
             */
            req.setAttribute("answer", "- Нужно указывать только цифры. Попробуйте снова.");
        }

        /*
        Перенаправляем наш запрос на вторую страницу,
        где и будем выводить наш ответ
         */
        req.getRequestDispatcher("result.jsp").forward(req, resp);
    }
}
