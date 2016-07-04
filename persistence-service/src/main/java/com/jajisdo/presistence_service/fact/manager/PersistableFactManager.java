package com.jajisdo.presistence_service.fact.manager;

import com.jajisdo.presistence_service.persistable.PersistableFact;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Map;


/**
 * Created by domann on 06.11.15.
 */
public class PersistableFactManager {

    // region class fields

    // jpa entity manager which handles entity persistence.
    private final EntityManagerFactory managerFactory;

    // endregion

    // region private helper methods

    /*
     * create a entity manager and begin a transaction.
     */
    private EntityManager createEmAndBeginTransaction() {
        EntityManager em = managerFactory.createEntityManager();
        em.getTransaction().begin();
        return em;
    }

    /*
     * commit the transaction and close the entity manager.
     */
    private void comitTransactionAndCloseEm(EntityManager em) {
        em.getTransaction().commit();
        em.close();
    }

    // endregion

    // regions constructors

    public PersistableFactManager() {
        managerFactory = Persistence.createEntityManagerFactory("unit");
    }

    public PersistableFactManager(String persistenceUnitName) {
        managerFactory = Persistence.createEntityManagerFactory(persistenceUnitName);
    }

    // endregion

    // region update database methods

    /**
     * Persist a given persistableFact.
     *
     * @return the assign database id of battery.
     */
    public long add(PersistableFact persistableFact) {
        EntityManager em = createEmAndBeginTransaction();

        em.persist(persistableFact);

        comitTransactionAndCloseEm(em);

        return persistableFact.getId();
    }

    /**
     * Update a given persistableFact.
     *
     * @return the assign database id of charging point.
     */
    public long update(PersistableFact persistableFact) {
        EntityManager em = createEmAndBeginTransaction();

        em.merge(persistableFact);

        comitTransactionAndCloseEm(em);

        return persistableFact.getId();
    }

    // endregion

    // region query methods

    public <T extends PersistableFact> T getFactById(Class<T > clazz, long factId) {

        EntityManager em = createEmAndBeginTransaction();

        T t = em.find(clazz, factId);

        comitTransactionAndCloseEm(em);

        return t;
    }

//    public Book getBookByAddress(URL address) {
//        Book book = null;
//
//        EntityManager em = createEmAndBeginTransaction();
//
//        TypedQuery<Book> query = em.createQuery(
//                "SELECT b FROM Book b WHERE b.downloadUrl = :address", Book.class);
//        query.setParameter("address", address);
//
//        List<Book> resultList = query.setMaxResults(1).getResultList();
//        if(!resultList.isEmpty()) {
//            book = resultList.get(0);
//        }
//
//        comitTransactionAndCloseEm(em);
//
//        return book;
//    }
//
//    public Book getLatestBook(Platform platform) {
//        Book book = null;
//
//        EntityManager em = createEmAndBeginTransaction();
//
//        TypedQuery<Book> query = em.createQuery(
//                "SELECT b FROM Book b WHERE b.platform = :platform ORDER BY b.id DESC", Book.class);
//        query.setParameter("platform", platform);
//
//        List<Book> resultList = query.setMaxResults(1).getResultList();
//        if(!resultList.isEmpty()) {
//            book = resultList.get(0);
//        }
//
//        comitTransactionAndCloseEm(em);
//
//        return book;
//    }
//
//    public Platform getPlatformById(long platformId) {
//        Platform platform = null;
//
//        EntityManager em = createEmAndBeginTransaction();
//
//        platform = em.find(Platform.class, platformId);
//
//        comitTransactionAndCloseEm(em);
//
//        return platform;
//    }
//
//    public Platform getPlatformByAddress(URL address) {
//        Platform platform = null;
//
//        EntityManager em = createEmAndBeginTransaction();
//
//        TypedQuery<Platform> query = em.createQuery(
//                "SELECT p FROM Platform p WHERE p.address = :address", Platform.class);
//        query.setParameter("address", address);
//
//        List<Platform> resultList = query.setMaxResults(1).getResultList();
//        if(!resultList.isEmpty()) {
//            platform = resultList.get(0);
//        }
//
//        comitTransactionAndCloseEm(em);
//
//        return platform;
//    }

    public long count(Class<? extends PersistableFact> clazz) {

        EntityManager em = createEmAndBeginTransaction();

        long count = em.createQuery(String.format("SELECT COUNT(c) FROM %s c", clazz.getSimpleName()), Long.class)
                .getSingleResult();

        comitTransactionAndCloseEm(em);

        return count;
    }

    public String getPersistenceUnitName() {
        return managerFactory.getProperties().get("hibernate.ejb.persistenceUnitName").toString();
    }

    public String getJdbcInfo() {
        StringBuilder builder = new StringBuilder();
        Map<String, Object> properties = managerFactory.getProperties();
        builder.append(String.format("jdbc.driver: %s%n", properties.get("javax.persistence.jdbc.driver")));
        builder.append(String.format("jdbc.user: %s%n", properties.get("javax.persistence.jdbc.user")));
        builder.append(String.format("jdbc.url: %s%n", properties.get("javax.persistence.jdbc.url")));
        return builder.toString();
    }

    // endregion

//    /**
//     * Persist a given battery.
//     * @return
//     *          the assign database id of battery.
//     */
//    public int add(BatteryEntity battery){
//        EntityManager em = createEmAndBeginTransaction();
//
//        em.persist(battery);
//
//        comitTransactionAndCloseEm(em);
//
//        return battery.getId();
//    }
//
//    /**
//     * Persist a given charging point.
//     * @return
//     *          the assign database id of charging point.
//     */
//    public int add(ChargingPointEntity chargingPoint){
//        EntityManager em = createEmAndBeginTransaction();
//
//        em.persist(chargingPoint);
//
//        comitTransactionAndCloseEm(em);
//
//        return chargingPoint.getId();
//    }
//
//    /**
//     * Update a given charging point.
//     * @return
//     *          the assign database id of charging point.
//     */
//    public int update(ChargingPointEntity chargingPoint){
//        EntityManager em = createEmAndBeginTransaction();
//
//        em.merge(chargingPoint);
//
//        comitTransactionAndCloseEm(em);
//
//        return chargingPoint.getId();
//    }

//    /**
//     * Update a given battery.
//     * @return
//     *          the assign database id of battery.
//     */
//    public int update(BatteryEntity battery){
//        EntityManager em = createEmAndBeginTransaction();
//
//        em.merge(battery);
//
//        comitTransactionAndCloseEm(em);
//
//        return battery.getId();
//    }

//    /**
//     * Persist a given battery measurement.
//     * @return
//     *          the assign database id of measurement.
//     */
//    public int add(BatteryMeasurementEntity batteryMeasurement){
//        EntityManager em = createEmAndBeginTransaction();
//
//        em.persist(batteryMeasurement);
//
//        comitTransactionAndCloseEm(em);
//
//        return batteryMeasurement.getId();
//    }
//
//    // endregion
//
//    // region query methods
//
//    public ChargingPointEntity getChargingPointById(int chargingPointId) {
//        ChargingPointEntity chargingPoint = null;
//
//        EntityManager em = createEmAndBeginTransaction();
//
//        chargingPoint = em.find(ChargingPointEntity.class, chargingPointId);
//
//        comitTransactionAndCloseEm(em);
//
//        return chargingPoint;
//    }
//
//    public BatteryEntity getBatteryById(int batteryId) {
//        BatteryEntity batteryEntity = null;
//
//        EntityManager em = createEmAndBeginTransaction();
//
//        batteryEntity = em.find(BatteryEntity.class, batteryId);
//
//        comitTransactionAndCloseEm(em);
//
//        return batteryEntity;
//    }
//
//    public BatteryMeasurementEntity getBatteryMeasurementById(int batteryMeasurementId) {
//        BatteryMeasurementEntity batteryMeasurementEntity = null;
//
//        EntityManager em = createEmAndBeginTransaction();
//
//        batteryMeasurementEntity = em.find(BatteryMeasurementEntity.class, batteryMeasurementId);
//
//        comitTransactionAndCloseEm(em);
//
//        return batteryMeasurementEntity;
//    }
//
//    /**
//     * Retrieve all battery entities from database.
//     * @return
//     *          all batteries contained in database.
//     */
//    public List<BatteryEntity> getAllBatteries() {
//        List<BatteryEntity> batteries = null;
//
//        EntityManager em = createEmAndBeginTransaction();
//
//        TypedQuery<BatteryEntity> query = em.createNamedQuery("BatteryEntity.getAll",
//                BatteryEntity.class);
//        batteries = query.getResultList();
//
//        comitTransactionAndCloseEm(em);
//
//        return batteries;
//    }
//
//    public BatteryEntity getBatteryByRelatedStorage(Storage storage){
//        return getBatteryIdByCode(storage.getName());
//    }
//
//    public BatteryEntity getBatteryIdByCode(String code) {
//        return getBatteryIdByCode(Integer.parseInt(code));
//    }
//
//    /**
//     * Retrieve a battery by id code from database.
//     * @return
//     *          A batteries contain in database with id_code == code.
//     */
//    public BatteryEntity getBatteryIdByCode(int code) {
//        BatteryEntity battery = null;
//
//        EntityManager em = createEmAndBeginTransaction();
//
//        TypedQuery<BatteryEntity> query = em.createQuery(
//                "SELECT b FROM BatteryEntity b WHERE b.idCode = :idcode",
//                BatteryEntity.class);
//        query.setParameter("idcode", code);
//        try {
//            battery = query.getSingleResult();
//        } catch (NoResultException nre) {}
//
//        comitTransactionAndCloseEm(em);
//
//        return battery;
//    }
//
//    /**
//     * Retrieve all {ChargingPointEntity} from database.
//     * @return
//     *          all charging point contained in database.
//     */
//    public List<ChargingPointEntity> getAllChargingPoints() {
//        List<ChargingPointEntity> chargingPoints = null;
//
//        EntityManager em = createEmAndBeginTransaction();
//
//        TypedQuery<ChargingPointEntity> query = em.createNamedQuery("ChargingPointEntity.getAll",
//                ChargingPointEntity.class);
//        chargingPoints = query.getResultList();
//
//        comitTransactionAndCloseEm(em);
//
//        return chargingPoints;
//    }
//
//    public ChargingPointEntity getChargingPointEntityByRelated(ChargingPoint chargingPoint){
//        return  null;
//    }
//
//    /**
//     * Retrieve a {ChargingPointEntity} from database identified by row and column position.
//     * @param row
//     *          the row position of the charging point.
//     * @param column
//     *          the column position of the charging point.
//     * @return
//     *          charging point in row and column position.
//     */
//    public ChargingPointEntity getChargingPointByPosition(int row, int column) {
//        ChargingPointEntity chargingPoint = null;
//
//        EntityManager em = createEmAndBeginTransaction();
//
//        TypedQuery<ChargingPointEntity> query = em.createQuery(
//                "SELECT c FROM ChargingPointEntity c WHERE c.positionRow = :row and c.positionColumn = :column",
//                ChargingPointEntity.class);
//        query.setParameter("row", row);
//        query.setParameter("column", column);
//        chargingPoint = query.getSingleResult();
//
//        comitTransactionAndCloseEm(em);
//
//        return chargingPoint;
//    }
//
//    /**
//     * Retrieve latest {BatteryMeasurementEntity} from database limited by {#numberOfResults}l.
//     * @param numberOfResults
//     *          number of returned measurements.
//     * @return
//     *          list of measurements limited by {#numberOfResults}.
//     */
//    public List<BatteryMeasurementEntity> getLatestBatteryMeasurements(int numberOfResults) {
//        List<BatteryMeasurementEntity> batteryMeasurements;
//
//        EntityManager em = createEmAndBeginTransaction();
//
//        TypedQuery<BatteryMeasurementEntity> query = em.createQuery(
//                "SELECT bm FROM BatteryMeasurementEntity bm ORDER BY bm.time DESC",
//                BatteryMeasurementEntity.class);
//        query.setMaxResults(numberOfResults);
//        batteryMeasurements = query.getResultList();
//
//        comitTransactionAndCloseEm(em);
//
//        return batteryMeasurements;
//    }
//
//    /**
//     * Retrieve latest {BatteryMeasurementEntity} from database limited by {#numberOfResults}, where
//     * the given battery is related to the requested measurements.
//     * @param batteryId
//     *          id of battery.
//     * @param numberOfResults
//     *          number of returned measurements.
//     * @return
//     *          list of measurements limited by {#numberOfResults}.
//     */
//    public List<BatteryMeasurementEntity> getLatestBatteryMeasurements(int batteryId, int numberOfResults) {
//        List<BatteryMeasurementEntity> batteryMeasurements;
//
//        EntityManager em = createEmAndBeginTransaction();
//
//        TypedQuery<BatteryMeasurementEntity> query = em.createQuery(
//                "SELECT bm FROM BatteryMeasurementEntity bm WHERE bm.battery.id = :bid ORDER BY bm.time DESC",
//                BatteryMeasurementEntity.class);
//        query.setParameter("bid", batteryId);
//        query.setMaxResults(numberOfResults);
//        batteryMeasurements = query.getResultList();
//
//        comitTransactionAndCloseEm(em);
//
//        return batteryMeasurements;
//    }
//
//    public List<BatteryMeasurementEntity> getBatteryMeasurements(int batteryId, TimeRange timeRange) {
//        List<BatteryMeasurementEntity> batteryMeasurements;
//
//        EntityManager em = createEmAndBeginTransaction();
//
//        TypedQuery<BatteryMeasurementEntity> query = em.createQuery(
//                "SELECT bm FROM BatteryMeasurementEntity bm " +
//                        "WHERE bm.battery.id = :bid " +
//                        "AND bm.time >= :starttime " +
//                        "AND bm.time <= :endtime " +
//                        "ORDER BY bm.time DESC",
//                BatteryMeasurementEntity.class);
//        query.setParameter("bid", batteryId);
//        query.setParameter("starttime", new Date(timeRange.getFrom()));
//        query.setParameter("endtime",new Date(timeRange.getTo()));
//        batteryMeasurements = query.getResultList();
//
//        comitTransactionAndCloseEm(em);
//
//        return batteryMeasurements;
//    }
//
//    /**
//     * Retrieve latest {BatteryMeasurementEntity} from database limited by {#numberOfResults}, where the charging point
//     * (identified by id) has a attached battery and this battery is related to the requested measurements.
//     * @param chargingPointId
//     *          charging point id.
//     * @param numberOfResults
//     *          number of returned measurements.
//     * @return
//     *          list of measurements limited by {#numberOfResults}.
//     */
//    public List<BatteryMeasurementEntity> getLastBatteryMeasurement(int chargingPointId, int numberOfResults) {
//        List<BatteryMeasurementEntity> batteryMeasurements;
//
//        EntityManager em = createEmAndBeginTransaction();
//
//        TypedQuery<BatteryMeasurementEntity> query = em.createQuery(
//                "SELECT bm FROM BatteryMeasurementEntity bm, ChargingPointEntity  cp " +
//                        "WHERE cp.id = :cpid " +
//                        "AND NOT cp.battery = null " +
//                        "AND bm.battery = cp.battery ",
//                BatteryMeasurementEntity.class);
//        query.setParameter("cpid", chargingPointId);
//        query.setMaxResults(numberOfResults);
//        batteryMeasurements = query.getResultList();
//
//        comitTransactionAndCloseEm(em);
//
//        return batteryMeasurements;
//    }
//
//    // endregion

}
