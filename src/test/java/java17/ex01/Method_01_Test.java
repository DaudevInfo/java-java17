package java17.ex01;

import java.util.List;

import org.junit.Test;

import java17.data.Data;
import java17.data.Person;


/**
 * Exercice 01 - Méthode par défaut
 */
public class Method_01_Test {

    // tag::IDao[]
    interface IDao {
        List<Person> findAll();

        // TODO créer une méthode int sumAge()
        // TODO Cette méthode retourne le résultat de l'addition des ages des personnes

        public default int sumAge() {
            //return findAll().stream().mapToInt(Person::getAge).sum();
            int age = 0;
            for (Person p :findAll()) {
                age =+ p.getAge();
            }
            return age;
        }
    }
    // end::IDao[]

    class DaoA implements IDao {

        List<Person> people = Data.buildPersonList(200);

        @Override
        public List<Person> findAll() {
            return people;
        }
    }

    class DaoB implements IDao {

        List<Person> people = Data.buildPersonList(100);

        @Override
        public List<Person> findAll() {
            return people;
        }
    }

    @Test
    public void test_daoA_sumAge() throws Exception {

        DaoA daoA = new DaoA();

        // TODO invoquer la méthode sumAge pour que le test soit passant

        int result = 0;
        result = daoA.sumAge();

        assert result == 200;
    }

    @Test
    public void test_daoB_sumAge() throws Exception {

        DaoB daoB = new DaoB();

        // TODO invoquer la méthode sumAge pour que le test soit passant
        int result = 0;
        result = daoB.sumAge();
        assert result == 100;

    }
}
