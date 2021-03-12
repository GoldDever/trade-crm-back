package ru.javamentor.init.product;

import org.springframework.stereotype.Component;
import ru.javamentor.model.product.Product;
import ru.javamentor.model.product.Supplier;
import ru.javamentor.repository.product.ManufacturerRepository;
import ru.javamentor.repository.product.ProductCategoryRepository;
import ru.javamentor.repository.product.ProductRepository;
import ru.javamentor.repository.product.SupplierRepository;
import ru.javamentor.repository.product.UnitRepository;

import java.math.BigDecimal;
import java.util.Set;

@Component
public class InitProductService {
    public InitProductService(ManufacturerRepository manufacturerRepository, SupplierRepository supplierRepository, ProductCategoryRepository productCategoryRepository, UnitRepository unitRepository, ProductRepository productRepository) {
        this.manufacturerRepository = manufacturerRepository;
        this.supplierRepository = supplierRepository;
        this.productCategoryRepository = productCategoryRepository;
        this.unitRepository = unitRepository;
        this.productRepository = productRepository;
    }

    private final ManufacturerRepository manufacturerRepository;
    private final SupplierRepository supplierRepository;
    private final ProductCategoryRepository productCategoryRepository;
    private final UnitRepository unitRepository;
    private final ProductRepository productRepository;

    public void initProduct() {
        createProduct(150, "Кофе молотый Жокей Ирландские сливки", "RUSSIA", 1L, 2L, 2L, "07938889", 10.00, 110.00, 18.00, 3, "1000000");

        createProduct(889, "Кофе капсульный Nescafe Dolce Gusto Капучино,", "RUSSIA", 2L, 2L, 2L, "29048329", 10.00, 500.00, 18.00, 4, "1000001");

        createProduct(578, "Кофе растворимый Nescafe Gold", "RUSSIA", 2L, 2L, 2L, "13767000", 10.00, 300.00, 18.00, 4, "1000002");

        createProduct(129, "Чай в пакетиках Greenfield Spicy Mango, зеленый, со вкусом и ароматом манго", "RUSSIA", 2L, 2L, 3L, "55321780", 10.00, 110, 18.00, 4, "1000003");

        createProduct(329, "Чай в пирамидках Greenfield Ice Cream Коллекция листового чая", "RUSSIA", 2L, 2L, 3L, "26143415", 10.00, 110, 18.00, 4, "1000004");

        createProduct(55, "Tess Pina Colada ароматизированный чай в пакетиках", "RUSSIA", 2L, 2L, 3L, "58450655", 10.00, 11, 18.00, 4, "1000005");

        createProduct(650, "Горячий шоколад какао 1 кг", "RUSSIA", 2L, 1L, 4L, "46953749", 10.00, 410, 18.00, 4, "1000006");

        createProduct(339, "Nesquik Opti-Start какао-напиток растворимый", "RUSSIA", 2L, 1L, 4L, "97405346", 10.00, 310, 18.00, 4, "1000007");

        createProduct(289, "Elza Hot Chocolate шоколад горячий, 325 г", "RUSSIA", 2L, 1L, 4L, "91316521", 10.00, 215, 18.00, 4, "1000008");

        createProduct(391, "Растворимое какао Carraro Cacao Amaro 250 гр", "RUSSIA", 2L, 1L, 4L, "36352160", 10.00, 215, 18.00, 4, "1000009");

        createProduct(32, "Слойка Мастер пирогов \"Венский конвертик\" с вишней, 70 г", "RUSSIA", 2L, 2L, 6L, "40746690", 10.00, 15, 18.00, 4, "1000010");

        createProduct(116, "Кекс С.Пудовъ \"Баунти кокосовый с шоколадом\"", "RUSSIA", 2L, 2L, 6L, "06346131", 10.00, 115, 18.00, 4, "1000011");

        createProduct(350, "Пирог Тирольские Пироги Клубника", "RUSSIA", 2L, 2L, 6L, "60711117", 10.00, 300.00, 18.00, 4, "1000012");

        createProduct(134, "Сладкая Сказка Love is печенье сдобное", "RUSSIA", 2L, 2L, 7L, "75448634", 10.00, 110.00, 18.00, 4, "1000013");

        createProduct(650, "Сладкая Сказка Печенье Подарки Деда Мороза", "RUSSIA", 2L, 2L, 7L, "71295343", 10.00, 600.00, 18.00, 4, "1000014");

        createProduct(250, "I Dolci Di Montagna Савоярди печенье сахарное для тирамису", "RUSSIA", 2L, 2L, 7L, "06616077", 10.00, 100.00, 18.00, 4, "1000015");

        createProduct(350, "Зефир Москва", "RUSSIA", 2L, 2L, 8L, "02309140", 10.00, 200.00, 18.00, 4, "1000016");

        createProduct(1350, "Пастила Питер", "RUSSIA", 2L, 2L, 8L, "94704139", 10.00, 1200.00, 18.00, 4, "1000017");

        createProduct(540, "Рамп Стейк из мраморной говядины Black Angus Мираторг", "RUSSIA", 2L, 2L, 10L, "37643743", 10.00, 320.00, 18.00, 4, "1000018");

        createProduct(789, "Мякоть бедра говяжья Мираторг Black Angus", "RUSSIA", 2L, 2L, 10L, "69154177", 10.00, 654.00, 18.00, 4, "1000019");

        createProduct(160, "Фарш свежий Черкизово Домашний, свинина, говядина, охлажденный", "RUSSIA", 2L, 2L, 10L, "57065457", 10.00, 110.00, 18.00, 4, "1000020");

        createProduct(190, "Фарш свежий  свинина, охлажденный", "RUSSIA", 2L, 2L, 11L, "37521101", 10.00, 167.00, 18.00, 4, "1000021");

        createProduct(250, "Свинина свежая Черкизово Эскалоп", "RUSSIA", 2L, 2L, 11L, "00965117", 10.00, 210.00, 18.00, 4, "1000022");

        createProduct(333, "Ребрышки Мираторг Деликатесные, свиные", "RUSSIA", 2L, 2L, 11L, "96036982", 10.00, 280.00, 18.00, 4, "1000023");

        createProduct(899, "Мясо охлажденное Лучезар Окорок из ягненка", "RUSSIA", 2L, 1L, 12L, "15690350", 10.00, 650.00, 18.00, 4, "1000024");

        createProduct(914, "Мясо охлажденное Meat Craft Котлета из молодого барашка на кости", "RUSSIA", 2L, 1L, 12L, "54228708", 10.00, 710.00, 18.00, 4, "1000025");

        createProduct(150, "Огурцы", "RUSSIA", 2L, 1L, 14L, "08369817", 10.00, 115.00, 18.00, 4, "1000026");

        createProduct(170, "Помидоры", "RUSSIA", 2L, 1L, 14L, "63930434", 10.00, 133.00, 18.00, 4, "1000027");

        createProduct(140, "Редиска", "RUSSIA", 2L, 1L, 14L, "73866646", 10.00, 110.00, 18.00, 4, "1000028");

        createProduct(67, "Бананы", "RUSSIA", 2L, 1L, 15L, "24099679", 10.00, 20.00, 18.00, 4, "1000029");

        createProduct(120, "Киви", "RUSSIA", 2L, 1L, 15L, "54670624", 10.00, 100.00, 18.00, 4, "1000030");

        createProduct(79, "Манго", "RUSSIA", 2L, 2L, 15L, "05753823", 10.00, 65.00, 18.00, 4, "1000031");

        createProduct(55, "Петрушка", "RUSSIA", 2L, 2L, 16L, "55416158", 10.00, 35.00, 18.00, 4, "1000032");

        createProduct(30, "Укроп", "RUSSIA", 2L, 2L, 16L, "26887090", 10.00, 21.00, 18.00, 4, "1000033");

        createProduct(89, "Салат", "RUSSIA", 2L, 2L, 16L, "56886317", 10.00, 67.00, 18.00, 4, "1000034");

        createProduct(150, "Колбаса Егорьевская колбасно-гастрономическая фабрика", "RUSSIA", 2L, 2L, 18L, "31306570", 10.00, 134.00, 18.00, 4, "1000035");

        createProduct(222, "Велком Докторская колбаса", "RUSSIA", 2L, 2L, 18L, "01829159", 10.00, 178.00, 18.00, 4, "1000036");

        createProduct(119, "Колбаса Черкизово Премиум \"Сальчичон\"", "RUSSIA", 2L, 2L, 18L, "75810082", 10.00, 88.00, 18.00, 4, "1000037");

        createProduct(159, "Дымов Бекон Венгерский сырокопченый, нарезка", "RUSSIA", 2L, 2L, 19L, "48297546", 10.00, 98.00, 18.00, 4, "1000038");

        createProduct(159, "Дымов Бекон Венгерский сырокопченый, нарезка", "RUSSIA", 2L, 2L, 19L, "94921835", 10.00, 98.00, 18.00, 4, "1000039");

        createProduct(209, "Дымов Бекон Мраморный копченовареный", "RUSSIA", 2L, 2L, 19L, "92989757", 10.00, 120.00, 18.00, 4, "1000040");

        createProduct(379, "Велком Карбонад копчено-вареный", "RUSSIA", 2L, 2L, 20L, "90826718", 10.00, 321.00, 18.00, 4, "1000041");

        createProduct(354, "Карбонад Мясной Дом Бородина", "RUSSIA", 2L, 2L, 20L, "47698336", 10.00, 311.00, 18.00, 4, "1000042");

        createProduct(569, "Карбонад Alto Concetto сыровяленый", "RUSSIA", 2L, 2L, 20L, "18481169", 10.00, 511.00, 18.00, 4, "1000043");

        createProduct(225, "Зефир КФ Нева Doni Zefironi ассорти", "RUSSIA", 2L, 2L, 8L, "63206915", 10.00, 189.00, 18.00, 4, "1000044");

        createProduct(77, "Суфле-маршмеллоу Haribo Chamallows Minis", "RUSSIA", 2L, 2L, 8L, "39800036", 10.00, 55.00, 18.00, 4, "1000045");

        createProduct(67, "Суфле-маршмеллоу Haribo Chamallows Minis", "RUSSIA", 2L, 2L, 8L, "68654270", 10.00, 45.00, 18.00, 4, "1000046");

        createProduct(222, "Зефир со вкусом апельсина на печенье глазированный ", "RUSSIA", 2L, 2L, 8L, "84148633", 10.00, 155.00, 18.00, 4, "1000047");

        createProduct(130, "Апельсины", "RUSSIA", 2L, 2L, 15L, "69880317", 10.00, 45.00, 18.00, 4, "1000048");

        createProduct(170, "Мандарины", "RUSSIA", 2L, 2L, 15L, "97628650", 10.00, 58.00, 18.00, 4, "1000049");
    }

    private void createProduct(int productCount, String productName, String madeCountry, long manufacturerId, long unitId, long productCategoryId, String article, double minMargin, double price, double standardMargin, int packagingCount, String idFromErp) {
        Product product = new Product();
        product.setProductCount(productCount);
        product.setProductName(productName);
        product.setMadeCountry(madeCountry);
        product.setManufacturer(manufacturerRepository.findManufacturerById(manufacturerId));
        Set<Supplier> suppliers = Set.copyOf(supplierRepository.findAll());
        product.setSuppliers(suppliers);
        product.setUnit(unitRepository.findUnitById(unitId));
        product.setProductCategory(productCategoryRepository.findById(productCategoryId).get());
        product.setArticle(article);
        product.setMinMargin(BigDecimal.valueOf(minMargin));
        product.setPrice(BigDecimal.valueOf(price));
        product.setStandardMargin(BigDecimal.valueOf(standardMargin));
        product.setPackagingCount(packagingCount);
        product.setIdFromErp(idFromErp);
        productRepository.save(product);
    }
}
