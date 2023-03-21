public class OrderTestData {
    public static final String[] COLOR_ALL = {"BLACK", "GREY"};
    public static final String[] COLOR_BLACK = {"BLACK"};
    public static final String[] COLOR_GREY = {"GREY"};
    public static final String[] COLOR_NO = {};
    public static final Object[][] TEST_DATA = new Object[][]{
            {
                    "Александр",
                    "Мышка",
                    "Лесопаркова 325",
                    4,
                    "89296636404",
                    5,
                    "2023-03-25",
                    "свежим",
                    COLOR_ALL
            },
            {
                    "Антон",
                    "Комолов",
                    "Деликова 12",
                    4,
                    "89296636404",
                    5,
                    "2023-04-06",
                    "вечером",
                    COLOR_BLACK
            },
            {
                    "Михаил",
                    "Задорнов",
                    "Варшавское 123",
                    4,
                    "89296636404",
                    5,
                    "2023-03-20",
                    "коммент",
                    COLOR_GREY
            },
            {
                    "Наруто",
                    "Узумаки",
                    "Коноха 23",
                    4,
                    "89651162689",
                    5,
                    "2023-06-12",
                    "коммент",
                    COLOR_NO
            }
    };
}
