package app;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {

    public void initSchema() throws SQLException {
        final String sql = """
            CREATE TABLE IF NOT EXISTS employees (
                id       SERIAL PRIMARY KEY,
                name     VARCHAR(255) NOT NULL,
                age      INTEGER,
                position VARCHAR(255),
                salary   REAL
            );
            """;
        try (Connection c = DatabaseConnector.getConnection();
             Statement st = c.createStatement()) {
            st.execute(sql);
        }
    }

    public Employee add(Employee e) throws SQLException {
        final String sql = """
            INSERT INTO employees (name, age, position, salary)
            VALUES (?, ?, ?, ?)
            RETURNING id;
            """;
        try (Connection c = DatabaseConnector.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, e.getName());
            if (e.getAge() == null) ps.setNull(2, Types.INTEGER); else ps.setInt(2, e.getAge());
            ps.setString(3, e.getPosition());
            if (e.getSalary() == null) ps.setNull(4, Types.REAL); else ps.setFloat(4, e.getSalary());

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) e.setId(rs.getInt(1));
            }
        }
        return e;
    }

    public boolean update(Employee e) throws SQLException {
        if (e.getId() == null) throw new IllegalArgumentException("id required for update");
        final String sql = """
            UPDATE employees
               SET name = ?, age = ?, position = ?, salary = ?
             WHERE id = ?;
            """;
        try (Connection c = DatabaseConnector.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, e.getName());
            if (e.getAge() == null) ps.setNull(2, Types.INTEGER); else ps.setInt(2, e.getAge());
            ps.setString(3, e.getPosition());
            if (e.getSalary() == null) ps.setNull(4, Types.REAL); else ps.setFloat(4, e.getSalary());
            ps.setInt(5, e.getId());
            return ps.executeUpdate() == 1;
        }
    }

    public boolean deleteById(int id) throws SQLException {
        final String sql = "DELETE FROM employees WHERE id = ?";
        try (Connection c = DatabaseConnector.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() == 1;
        }
    }

    public Employee findById(int id) throws SQLException {
        final String sql = "SELECT id, name, age, position, salary FROM employees WHERE id = ?";
        try (Connection c = DatabaseConnector.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return map(rs);
            }
        }
        return null;
    }

    public List<Employee> findAll() throws SQLException {
        final String sql = "SELECT id, name, age, position, salary FROM employees ORDER BY id";
        List<Employee> out = new ArrayList<>();
        try (Connection c = DatabaseConnector.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) out.add(map(rs));
        }
        return out;
    }

    private Employee map(ResultSet rs) throws SQLException {
        return new Employee(
                rs.getInt("id"),
                rs.getString("name"),
                (Integer) rs.getObject("age"),       // может быть NULL
                rs.getString("position"),
                rs.getObject("salary") == null ? null : rs.getFloat("salary")
        );
        // примечание: getObject позволяет различать NULL
    }
}
