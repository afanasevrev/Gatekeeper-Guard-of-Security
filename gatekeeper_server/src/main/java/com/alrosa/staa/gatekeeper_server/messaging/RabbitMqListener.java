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
                general.setComplete_name(main.getName());
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
}
