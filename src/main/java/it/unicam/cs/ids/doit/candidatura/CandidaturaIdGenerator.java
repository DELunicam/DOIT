package it.unicam.cs.ids.doit.candidatura;

import java.io.Serializable;
import java.sql.*;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

public class CandidaturaIdGenerator implements IdentifierGenerator {

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
        // SessionImplementor

        String prefix = "CAND";
        Connection connection = session.connection();

        try {
            Statement statement = connection.createStatement();

            ResultSet rs = statement.executeQuery("select max(Candidatura_Id) as Id from Candidatura");

            if (rs.next()) {
                int id = rs.getInt(1);// + 101;
                String idSt = String.valueOf("STRIG ID: " + id);
                System.out.println(idSt);
                String generatedId = prefix + String.valueOf(id);
                System.out.println("Generated Id: " + generatedId);
                return generatedId;
            }
            
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return null;
    }

}