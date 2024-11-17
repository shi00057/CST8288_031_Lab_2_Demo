import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import com.algonquincollege.lab2.DAO.IndyWinnerDAO;
import com.algonquincollege.lab2.DAO.impl.IndyWinnerDAOImpl;
import com.algonquincollege.lab2.Model.IndyWinner;
import com.algonquincollege.lab2.DB.DatabaseConnection;


@TestInstance(TestInstance.Lifecycle.PER_CLASS) // Reuse the DAO instance across all tests
class IndyWinnerDAOTest {

    private IndyWinnerDAO dao;

    /**
     * Initializes the DAO before running all tests.
     * Ensures that the DAO instance is successfully created.
     */
    @BeforeAll
    void setup() {
        dao = new IndyWinnerDAOImpl();
        // Ensure the DAO is initialized correctly
        assertNotNull(dao, "DAO initialization failed!");
    }

    /**
     * Tests if the database connection is successfully established.
     * Verifies that no exceptions are thrown during the connection process.
     */
    @Test
    @DisplayName("Test Database Connection")
    void testDatabaseConnection() {
        assertDoesNotThrow(() -> {
            assertNotNull(DatabaseConnection.getInstance(), "Database connection should not be null");
        }, "Database connection failed!");
    }

    /**
     * Tests the DAO method for fetching Indy winners with a limit.
     * Verifies that the returned list is not null and contains no more than 10 records.
     */
    @Test
    @DisplayName("Test Fetching Indy Winners with Limit")
    void testGetIndyWinners() {
        List<IndyWinner> winners = dao.getIndyWinners(0, 10);
        assertNotNull(winners, "The list of winners should not be null.");
        assertTrue(winners.size() <= 10, "The list size should be less than or equal to 10.");
    }

    /**
     * Tests the DAO method with an offset that exceeds the number of records in the database.
     * Verifies that an empty list is returned for out-of-bound offset values.
     */
    @Test
    @DisplayName("Test Empty Result")
    void testGetIndyWinnersEmptyResult() {
        List<IndyWinner> winners = dao.getIndyWinners(1000, 10); // Assuming the offset exceeds total records
        assertNotNull(winners, "The result should not be null.");
        assertTrue(winners.isEmpty(), "The result list should be empty for an out-of-bound offset.");
    }

    /**
     * Tests the data integrity of the retrieved Indy winners.
     * Verifies that the fields of the first winner object are properly populated.
     */
    @Test
    @DisplayName("Test Data Integrity")
    void testIndyWinnerData() {
        List<IndyWinner> winners = dao.getIndyWinners(0, 10);
        if (!winners.isEmpty()) {
            IndyWinner winner = winners.get(0);
            assertNotNull(winner.getYear(), "Year should not be null.");
            assertNotNull(winner.getDriver(), "Driver should not be null.");
            assertTrue(winner.getAverageSpeed() > 0, "Average speed should be positive.");
            assertNotNull(winner.getCountry(), "Country should not be null.");
        }
    }
}
