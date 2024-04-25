package com.alrosa.staa.gatekeeper_server.messaging;

import com.alrosa.staa.gatekeeper_server.db.*;
import com.alrosa.staa.gatekeeper_server.util.HibernateUtil;
import com.alrosa.staa.gatekeeper_server.variables.Variables;
import com.google.gson.Gson;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@EnableRabbit
@Component
public class RabbitMqListener {
    Logger logger = Logger.getLogger(RabbitMqListener.class);
    Gson gson = new Gson();
    String text;
    @Autowired
    private AmqpTemplate template;
    /**
     * Листенер получает от клиента объекты,
     * которые необходимо добавить в БД,
     * потом вернуть обратно с данными объект клиенту,
     * чтобы тот у себя добавил в дереве
     */
    @RabbitListener(queues = Variables.QUEUE_NAME)
    private void Queue(String message) {
        logger.info(message);
        General general = gson.fromJson(message, General.class);
        switch (general.getDirection()) {
            case MAIN:
                Main main = getMain(general.getId());
                logger.info(main.getId());
                general.setId(main.getId());
                general.setDirection(Direction.MAIN);
                general.setParentId(0);
                general.setComplete_name(main.getComplete_name());
                text = gson.toJson(general);
                template.convertAndSend(Variables.QUEUE_NAME_1, text);
                break;
            case SERVER:
                Server server = new Server("Сервер", "0.0.0.0", general.getParentId());
                try {
                    writeServer(server);
                } catch (IllegalStateException e) {
                    logger.error(e);
                }
                general.setId(server.getId());
                general.setComplete_name(server.getServer_name());
                general.setDirection(Direction.SERVER);
                general.setParentId(server.getParent_id());
                text = gson.toJson(general);
                template.convertAndSend(Variables.QUEUE_NAME_1, text);
                break;
            case BUREAU:
                Bureau bureau = new Bureau("Бюро", general.getParentId());
                try {
                    writeBureau(bureau);
                } catch (IllegalStateException e) {
                    logger.error(e);
                }
                general.setId(bureau.getId());
                general.setComplete_name(bureau.getBureau_name());
                general.setParentId(bureau.getParent_id());
                general.setDirection(Direction.BUREAU);
                text = gson.toJson(general);
                template.convertAndSend(Variables.QUEUE_NAME_1, text);
                break;
            case COMPUTER:
                Computer computer = new Computer("Компьютер", "0.0.0.0", general.getParentId());
                try {
                    writeComputer(computer);
                } catch (IllegalStateException e) {
                    logger.error(e);
                }
                general.setId(computer.getId());
                general.setComplete_name(computer.getComputer_name());
                general.setParentId(computer.getParent_id());
                general.setDirection(Direction.COMPUTER);
                text = gson.toJson(general);
                template.convertAndSend(Variables.QUEUE_NAME_1, text);
                break;
            case USERS:
                Users users = new Users("Пользователи", general.getParentId());
                try {
                    writeUsers(users);
                } catch (IllegalStateException e) {
                    logger.error(e);
                }
                general.setId(users.getId());
                general.setComplete_name(users.getUsers_name());
                general.setParentId(users.getParent_id());
                general.setDirection(Direction.USERS);
                text = gson.toJson(general);
                template.convertAndSend(Variables.QUEUE_NAME_1, text);
                break;
            case ADMINS:
                Admins admins = new Admins("Администраторы", general.getParentId());
                try {
                    writeAdmins(admins);
                } catch (IllegalStateException e) {
                    logger.error(e);
                }
                general.setId(admins.getId());
                general.setComplete_name(admins.getAdmins_name());
                general.setParentId(admins.getParent_id());
                general.setDirection(Direction.ADMINS);
                text = gson.toJson(general);
                template.convertAndSend(Variables.QUEUE_NAME_1, text);
                break;
            case OPERATORS:
                Operators operators = new Operators("Операторы", general.getParentId());
                try {
                    writeOperators(operators);
                } catch (IllegalStateException e) {
                    logger.error(e);
                }
                general.setId(operators.getId());
                general.setComplete_name(operators.getOperators_name());
                general.setParentId(operators.getParent_id());
                general.setDirection(Direction.OPERATORS);
                text = gson.toJson(general);
                template.convertAndSend(Variables.QUEUE_NAME_1, text);
                break;
            case GLOBAL_ACCESS_LEVELS:
                GlobalAccessLevels globalAccessLevels = new GlobalAccessLevels("Глобальный уровень доступа", general.getParentId());
                try{
                    writeGlobalAccessLevels(globalAccessLevels);
                } catch (IllegalStateException e) {
                    logger.error(e);
                }
                general.setId(globalAccessLevels.getId());
                general.setComplete_name(globalAccessLevels.getGlobal_access_levels_name());
                general.setParentId(globalAccessLevels.getParent_id());
                general.setDirection(Direction.GLOBAL_ACCESS_LEVELS);
                text = gson.toJson(general);
                template.convertAndSend(Variables.QUEUE_NAME_1, text);
                break;
            case CARDS:
                Cards cards = new Cards("Карты доступа", general.getParentId());
                try{
                    writeCards(cards);
                } catch(IllegalStateException e) {
                    logger.error(e);
                }
                general.setId(cards.getId());
                general.setComplete_name(cards.getCards_name());
                general.setParentId(cards.getParent_id());
                general.setDirection(Direction.CARDS);
                text = gson.toJson(general);
                template.convertAndSend(Variables.QUEUE_NAME_1, text);
                break;
            case CARD_LAYOUTS:
                CardLayouts cardLayouts = new CardLayouts("Макеты карт", general.getParentId());
                try {
                    writeCardLayouts(cardLayouts);
                } catch(IllegalStateException e) {
                    logger.error(e);
                }
                general.setId(cardLayouts.getId());
                general.setComplete_name(cardLayouts.getCard_layouts_name());
                general.setParentId(cardLayouts.getParent_id());
                general.setDirection(Direction.CARD_LAYOUTS);
                text = gson.toJson(general);
                template.convertAndSend(Variables.QUEUE_NAME_1, text);
                break;
            case POSITIONS:
                Positions positions = new Positions("Должности", general.getParentId());
                try {
                    writePositions(positions);
                } catch (IllegalStateException e) {
                    logger.error(e);
                }
                general.setId(positions.getId());
                general.setComplete_name(positions.getPositions_name());
                general.setParentId(positions.getParent_id());
                general.setDirection(Direction.POSITIONS);
                text = gson.toJson(general);
                template.convertAndSend(Variables.QUEUE_NAME_1, text);
                break;
            case ORGANIZATIONS:
                Organizations organizations = new Organizations("Организации", general.getParentId());
                try {
                    writeOrganizations(organizations);
                } catch (IllegalStateException e) {
                    logger.error(e);
                }
                general.setId(organizations.getId());
                general.setComplete_name(organizations.getOrganizations_name());
                general.setParentId(organizations.getParent_id());
                general.setDirection(Direction.ORGANIZATIONS);
                text = gson.toJson(general);
                template.convertAndSend(Variables.QUEUE_NAME_1, text);
                break;
            case PERCO:
                Perco perco = new Perco("Контроллеры Perco", general.getParentId());
                try {
                    writePerco(perco);
                } catch (IllegalStateException e) {
                    logger.error(e);
                }
                general.setId(perco.getId());
                general.setComplete_name(perco.getPerco_name());
                general.setParentId(perco.getParent_id());
                general.setDirection(Direction.PERCO);
                text = gson.toJson(general);
                template.convertAndSend(Variables.QUEUE_NAME_1, text);
                break;
            case PERCOC01:
                PercoC01 percoc01 = new PercoC01("Контроллер PercoC01","0.0.0.0",false, general.getParentId());
                try {
                    writePercoC01(percoc01);
                } catch (IllegalStateException e) {
                    logger.error(e);
                }
                general.setId(percoc01.getId());
                general.setComplete_name(percoc01.getPercoc01_name());
                general.setParentId(percoc01.getParent_id());
                general.setDirection(Direction.PERCOC01);
                text = gson.toJson(general);
                template.convertAndSend(Variables.QUEUE_NAME_1, text);
                break;
            case CARDREADER:
                CardReader cardReader = new CardReader("Считыватель", 0, "Wiegand", general.getParentId());
                try {
                    writeCardReader(cardReader);
                } catch (IllegalStateException e) {
                    logger.error(e);
                }
                general.setId(cardReader.getId());
                general.setComplete_name(cardReader.getCard_reader_name());
                general.setParentId(cardReader.getParent_id());
                general.setDirection(Direction.CARDREADER);
                text = gson.toJson(general);
                template.convertAndSend(Variables.QUEUE_NAME_1, text);
                break;
            case CONSOLE:
                Console console = new Console("Консоль", general.getParentId());
                try {
                    writeConsole(console);
                } catch (IllegalStateException e) {
                    logger.error(e);
                }
                general.setId(console.getId());
                general.setComplete_name(console.getConsole_name());
                general.setParentId(console.getParent_id());
                general.setDirection(Direction.CONSOLE);
                text = gson.toJson(general);
                template.convertAndSend(Variables.QUEUE_NAME_1, text);
                break;
            case PASS_OFFICE:
                PassOffice passOffice = new PassOffice("Бюро пропусков", general.getParentId());
                try {
                    writePassOffice(passOffice);
                } catch (IllegalStateException e) {
                    logger.error(e);
                }
                general.setId(passOffice.getId());
                general.setComplete_name(passOffice.getPass_office_name());
                general.setParentId(passOffice.getParent_id());
                general.setDirection(Direction.PASS_OFFICE);
                text = gson.toJson(general);
                template.convertAndSend(Variables.QUEUE_NAME_1, text);
                break;
            case MAN_USER:
                ManUser manUser = new ManUser("Человек", "", "", "", Variables.GENDER_MAN, general.getParentId());
                try {
                    writeManUser(manUser);
                } catch (IllegalStateException e) {
                    logger.error(e);
                }
                general.setId(manUser.getId());
                general.setComplete_name(manUser.getComplete_name());
                general.setParentId(manUser.getParent_id());
                general.setDirection(Direction.MAN_USER);
                text = gson.toJson(general);
                template.convertAndSend(Variables.QUEUE_NAME_1, text);
                break;
            case WOMAN_USER:
                WomanUser womanUser = new WomanUser("Человек", "", "", "", Variables.GENDER_WOMAN, general.getParentId());
                try {
                    writeWomanUser(womanUser);
                } catch (IllegalStateException e) {
                    logger.error(e);
                }
                general.setId(womanUser.getId());
                general.setComplete_name(womanUser.getComplete_name());
                general.setParentId(womanUser.getParent_id());
                general.setDirection(Direction.WOMAN_USER);
                text = gson.toJson(general);
                template.convertAndSend(Variables.QUEUE_NAME_1, text);
                break;
            case MAN_OPERATOR:
                ManOperator manOperator = new ManOperator("Человек","","","",Variables.GENDER_MAN, general.getParentId());
                try {
                    writeManOperator(manOperator);
                } catch (IllegalStateException e) {
                    logger.error(e);
                }
                general.setId(manOperator.getId());
                general.setComplete_name(manOperator.getComplete_name());
                general.setParentId(manOperator.getParent_id());
                general.setDirection(Direction.MAN_OPERATOR);
                text = gson.toJson(general);
                template.convertAndSend(Variables.QUEUE_NAME_1, text);
                break;
            case WOMAN_OPERATOR:
                WomanOperator womanOperator = new WomanOperator("Человек", "", "", "", Variables.GENDER_WOMAN, general.getParentId());
                try {
                    writeWomanOperator(womanOperator);
                } catch (IllegalStateException e) {
                    logger.error(e);
                }
                general.setId(womanOperator.getId());
                general.setComplete_name(womanOperator.getComplete_name());
                general.setParentId(womanOperator.getParent_id());
                general.setDirection(Direction.WOMAN_OPERATOR);
                text = gson.toJson(general);
                template.convertAndSend(Variables.QUEUE_NAME_1, text);
                break;
            case MAN_ADMIN:
                ManAdmin manAdmin = new ManAdmin("Человек", "", "", "", Variables.GENDER_MAN, general.getParentId());
                try {
                    writeManAdmin(manAdmin);
                } catch (IllegalStateException e) {
                    logger.error(e);
                }
                general.setId(manAdmin.getId());
                general.setComplete_name(manAdmin.getComplete_name());
                general.setParentId(manAdmin.getParent_id());
                general.setDirection(Direction.MAN_ADMIN);
                text = gson.toJson(general);
                template.convertAndSend(Variables.QUEUE_NAME_1, text);
                break;
            case WOMAN_ADMIN:
                WomanAdmin womanAdmin = new WomanAdmin("Человек", "", "", "", Variables.GENDER_WOMAN, general.getParentId());
                try {
                    writeWomanAdmin(womanAdmin);
                } catch (IllegalStateException e) {
                    logger.error(e);
                }
                general.setId(womanAdmin.getId());
                general.setComplete_name(womanAdmin.getComplete_name());
                general.setParentId(womanAdmin.getParent_id());
                general.setDirection(Direction.WOMAN_ADMIN);
                text = gson.toJson(general);
                template.convertAndSend(Variables.QUEUE_NAME_1, text);
                break;
            case MAN_PASS_OFFICE:
                ManPassOffice manPassOffice = new ManPassOffice("Человек", "", "", "", Variables.GENDER_MAN, general.getParentId());
                try {
                    writeManPassOffice(manPassOffice);
                } catch (IllegalStateException e) {
                    logger.error(e);
                }
                general.setId(manPassOffice.getId());
                general.setComplete_name(manPassOffice.getComplete_name());
                general.setParentId(manPassOffice.getParent_id());
                general.setDirection(Direction.MAN_PASS_OFFICE);
                text = gson.toJson(general);
                template.convertAndSend(Variables.QUEUE_NAME_1, text);
                break;
            case WOMAN_PASS_OFFICE:
                WomanPassOffice womanPassOffice = new WomanPassOffice("Человек", "", "", "", Variables.GENDER_WOMAN, general.getParentId());
                try {
                    writeWomanPassOffice(womanPassOffice);
                } catch (IllegalStateException e) {
                    logger.error(e);
                }
                general.setId(womanPassOffice.getId());
                general.setComplete_name(womanPassOffice.getComplete_name());
                general.setParentId(womanPassOffice.getParent_id());
                general.setDirection(Direction.WOMAN_PASS_OFFICE);
                text = gson.toJson(general);
                template.convertAndSend(Variables.QUEUE_NAME_1, text);
                break;
            case GLOBAL_ACCESS_LEVEL:
                GlobalAccessLevel globalAccessLevel = new GlobalAccessLevel("Глобальный уровень доступа", general.getParentId());
                try {
                    writeGlobalAccessLevel(globalAccessLevel);
                } catch (IllegalStateException e) {
                    logger.error(e);
                }
                general.setId(globalAccessLevel.getId());
                general.setComplete_name(globalAccessLevel.getGlobal_access_level_name());
                general.setParentId(globalAccessLevel.getParent_id());
                general.setDirection(Direction.GLOBAL_ACCESS_LEVEL);
                text = gson.toJson(general);
                template.convertAndSend(Variables.QUEUE_NAME_1, text);
            case CARD:
                Card card = new Card("Карта доступа", 0, general.getParentId());
                try {
                    writeCard(card);
                } catch (IllegalStateException e) {
                    logger.error(e);
                }
                general.setId(card.getId());
                general.setComplete_name(card.getCard_name());
                general.setParentId(card.getParent_id());
                general.setDirection(Direction.CARD);
                text = gson.toJson(general);
                template.convertAndSend(Variables.QUEUE_NAME_1, text);
            default:
                template.convertAndSend(Variables.QUEUE_NAME_1, "Этот вопрос ещё не проработан");
                break;
        }
    }
    /**
     * Метод вытягивает из БД главный класс по указанному ID
     */
    private synchronized Main getMain(int id) {
        Main main = null;
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Старт транзакции
            transaction = session.beginTransaction();
            main = session.get(Main.class, id);
            // Коммит транзакции
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return main;
    }
    /**
     * Метод записывает в БД объект Сервер
     * @param server
     */
    private synchronized void writeServer(Server server) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Старт транзакции
            transaction = session.beginTransaction();
            // Добавим в БД сервер
            session.persist(server);
            // Коммит транзакции
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
    /**
     * Метод записывает в БД объект Бюро
     */
    private synchronized void writeBureau(Bureau bureau) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Старт транзакции
            transaction = session.beginTransaction();
            // Добавим в БД сервер
            session.persist(bureau);
            // Коммит транзакции
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
    /**
     * Метод записывает в БД объект Компьютер
     */
    private synchronized void writeComputer(Computer computer) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Старт транзакции
            transaction = session.beginTransaction();
            // Добавим в БД сервер
            session.persist(computer);
            // Коммит транзакции
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
    /**
     * Метод записывает в БД объект Пользователи
     */
    private synchronized void writeUsers(Users users) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Старт транзакции
            transaction = session.beginTransaction();
            // Добавим в БД сервер
            session.persist(users);
            // Коммит транзакции
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
    /**
     * Метод записывает в БД объект Администраторы
     */
    private synchronized void writeAdmins(Admins admins) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Старт транзакции
            transaction = session.beginTransaction();
            // Добавим в БД сервер
            session.persist(admins);
            // Коммит транзакции
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
    /**
     * Метод записывает в БД объект Операторы
     */
    private synchronized void writeOperators(Operators operators) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Старт транзакции
            transaction = session.beginTransaction();
            // Добавим в БД сервер
            session.persist(operators);
            // Коммит транзакции
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
    /**
     * Метод записывает в БД объект Глобальные уровни доступа
     */
    private synchronized void writeGlobalAccessLevels(GlobalAccessLevels globalAccessLevels) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Старт транзакции
            transaction = session.beginTransaction();
            // Добавим в БД сервер
            session.persist(globalAccessLevels);
            // Коммит транзакции
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
    /**
     * Метод записывает в БД объект Карты доступа
     */
    private synchronized void writeCards(Cards cards) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Старт транзакции
            transaction = session.beginTransaction();
            // Добавим в БД сервер
            session.persist(cards);
            // Коммит транзакции
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
    /**
     * Метод записывает в БД объект Макеты карт
     */
    private synchronized void writeCardLayouts(CardLayouts cardLayouts) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Старт транзакции
            transaction = session.beginTransaction();
            // Добавим в БД сервер
            session.persist(cardLayouts);
            // Коммит транзакции
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
    /**
     * Метод записывает в БД объект Должности
     * @param positions
     */
    private synchronized void writePositions(Positions positions) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Старт транзакции
            transaction = session.beginTransaction();
            // Добавим в БД сервер
            session.persist(positions);
            // Коммит транзакции
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
    /**
     * Метод записывает в БД объект Организации
     * @param organizations
     */
    private synchronized void writeOrganizations(Organizations organizations) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Старт транзакции
            transaction = session.beginTransaction();
            // Добавим в БД сервер
            session.persist(organizations);
            // Коммит транзакции
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
    /**
     * Метод добавляет в БД контроллеры Perco(Обобщенное наименование)
     * @param perco
     */
    private synchronized void writePerco(Perco perco){
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Старт транзакции
            transaction = session.beginTransaction();
            // Добавим в БД сервер
            session.persist(perco);
            // Коммит транзакции
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
    /**
     * Метод добавляет в БД контроллеры PercoC01
     * @param percoc01
     */
    private synchronized void writePercoC01(PercoC01 percoc01){
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Старт транзакции
            transaction = session.beginTransaction();
            // Добавим в БД сервер
            session.persist(percoc01);
            // Коммит транзакции
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    /**
     * Метод добавляет в БД Считыватели под контроллер PERCO-C01
     * @param cardReader
     */
    private synchronized void writeCardReader(CardReader cardReader){
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Старт транзакции
            transaction = session.beginTransaction();
            // Добавим в БД сервер
            session.persist(cardReader);
            // Коммит транзакции
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    /**
     * Метод записывает в БД Консоли
     * @param console
     */
    private synchronized void writeConsole(Console console) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Старт транзакции
            transaction = session.beginTransaction();
            // Добавим в БД сервер
            session.persist(console);
            // Коммит транзакции
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
    /**
     * Метод записывает в БД Бюро пропусков
     * @param passOffice
     */
    private synchronized void writePassOffice(PassOffice passOffice) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Старт транзакции
            transaction = session.beginTransaction();
            // Добавим в БД сервер
            session.persist(passOffice);
            // Коммит транзакции
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
    /**
     * Метод записывает в БД пользователей мужского пола
     * @param manUser
     */
    private synchronized void writeManUser(ManUser manUser) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Старт транзакции
            transaction = session.beginTransaction();
            // Добавим в БД сервер
            session.persist(manUser);
            // Коммит транзакции
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
    /**
     * Метод записывает в БД пользователей женского пола
     * @param womanUser
     */
    private synchronized void writeWomanUser(WomanUser womanUser) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Старт транзакции
            transaction = session.beginTransaction();
            // Добавим в БД сервер
            session.persist(womanUser);
            // Коммит транзакции
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
    /**
     * Метод записывает в БД операторов мужского пола
     * @param manOperator
     */
    private synchronized void writeManOperator(ManOperator manOperator) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Старт транзакции
            transaction = session.beginTransaction();
            // Добавим в БД сервер
            session.persist(manOperator);
            // Коммит транзакции
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
    /**
     * Метод записывает в БД операторов женского пола
     * @param womanOperator
     */
    private synchronized void writeWomanOperator(WomanOperator womanOperator) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Старт транзакции
            transaction = session.beginTransaction();
            // Добавим в БД сервер
            session.persist(womanOperator);
            // Коммит транзакции
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
    /**
     * Метод записывает в БД администраторов мусжкого пола
     * @param manAdmin
     */
    private synchronized void writeManAdmin(ManAdmin manAdmin) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Старт транзакции
            transaction = session.beginTransaction();
            // Добавим в БД сервер
            session.persist(manAdmin);
            // Коммит транзакции
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
    /**
     * Метод записывает в БД администраторов женского пола
     * @param womanAdmin
     */
    private synchronized void writeWomanAdmin(WomanAdmin womanAdmin) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Старт транзакции
            transaction = session.beginTransaction();
            // Добавим в БД сервер
            session.persist(womanAdmin);
            // Коммит транзакции
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
    /**
     * Метод записывает в БД инспекторов бюро пропусков женского пола
     * @param manPassOffice
     */
    private synchronized void writeManPassOffice(ManPassOffice manPassOffice) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Старт транзакции
            transaction = session.beginTransaction();
            // Добавим в БД сервер
            session.persist(manPassOffice);
            // Коммит транзакции
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
    /**
     * Метод записывает в БД инспекторов бюро пропусков мужского пола
     * @param womanPassOffice
     */
    private synchronized void writeWomanPassOffice(WomanPassOffice womanPassOffice) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Старт транзакции
            transaction = session.beginTransaction();
            // Добавим в БД сервер
            session.persist(womanPassOffice);
            // Коммит транзакции
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
    /**
     * Метод записывает в БД глобальный уровень доступа
     * @param globalAccessLevel
     */
    private synchronized void writeGlobalAccessLevel(GlobalAccessLevel globalAccessLevel) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Старт транзакции
            transaction = session.beginTransaction();
            // Добавим в БД сервер
            session.persist(globalAccessLevel);
            // Коммит транзакции
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
    /**
     * Метод записывает в БД карты доступа
     * @param card
     */
    private synchronized void writeCard(Card card) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Старт транзакции
            transaction = session.beginTransaction();
            // Добавим в БД сервер
            session.persist(card);
            // Коммит транзакции
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}
