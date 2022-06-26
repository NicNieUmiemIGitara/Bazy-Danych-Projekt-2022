package insides.Server;

import insides.SpecifiedClasses.ClassInfo;
import insides.SpecifiedClasses.Headmaster.*;
import insides.SpecifiedClasses.LoginProcess;
import insides.SpecifiedClasses.Student.StudentClassWeekSchedule;
import insides.SpecifiedClasses.Student.StudentFrequency;
import insides.SpecifiedClasses.Student.StudentMarks;
import insides.SpecifiedClasses.Student.StudentTestSchedule;
import insides.SpecifiedClasses.Teacher.*;
import insides.TableClasses.Adresy;
import insides.TableClasses.Dyrektor;
import insides.TableClasses.Nauczyciele;
import insides.TableClasses.Uczniowie;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Scanner;
import java.util.Vector;

public class Server {
    public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException {
        int userType;// 1 - nauczyciel :::::::: 2 - uczen :::::::: 3 - dyrektor
        int instructionID;
        Uczniowie uczen = new Uczniowie();
        Nauczyciele nauczyciel = new Nauczyciele();
        Dyrektor dyrektor = new Dyrektor();
        ServerSocket server = new ServerSocket(42069);
        //Socket socket = server.accept();
        //ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        String host = "localhost";
        int port = 1521;
        //String database = "xe";
        //String user = "demosql";
        //String password = "demosql";
        Scanner scan = new Scanner(System.in);
        System.out.println("USER: ");
        String user = scan.nextLine();
        System.out.println("PASSWORD: ");
        String password = scan.nextLine();
        System.out.println("DATABASE: ");
        String database = scan.nextLine();

        String url = "jdbc:oracle:thin:" + user + "/" + password + "@" + host + ":" + port + ":" + database;
        Connection connection = DriverManager.getConnection(url);
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        StudentMarks tmp = new StudentMarks();
        tmp.setMarks(connection, 22);
        Vector<Integer> v = new Vector<>();
        v.add(1);
        v.add(2);
        System.out.println(v);
        System.out.println(tmp);

/*
        try (Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(
                "SELECT (MAX(id_adresu)) FROM adresy")) {
            while (resultSet.next()) {
                System.out.println(resultSet.getInt(1));
            }
        }*/
        for (; ; ) {
            Socket connectionSocket = server.accept();
            ObjectInputStream serverIn = new ObjectInputStream(connectionSocket.getInputStream());
            ObjectOutputStream serverOut = new ObjectOutputStream(connectionSocket.getOutputStream());
            instructionID = (int) serverIn.readObject();
            if (instructionID == 0) {
                LoginProcess l = new LoginProcess();
                userType = l.login(connection, (String) serverIn.readObject(), (String) serverIn.readObject(), uczen, nauczyciel, dyrektor);
                serverOut.writeObject(userType);
                if (userType == 1) {
                    //System.out.println(nauczyciel);
                    ClassList cL = new ClassList();
                    cL.setList(connection);
                    serverOut.writeObject(nauczyciel);
                    serverOut.writeObject(cL.getList());
                    //System.out.println("dupa");
                } else if (userType == 2) {
                    serverOut.writeObject(uczen);
                    ClassInfo classInfo = new ClassInfo();
                    classInfo.setKlasa(connection, (int) serverIn.readObject());
                    serverOut.writeObject(classInfo.getKlasa());
                    //serverOut.writeObject(uczen);1
                } else if (userType == 3) {
                    serverOut.writeObject(dyrektor);
                    //serverOut.writeObject(dyrektor);
                }

                //l.login(connection, (String) serverIn.readObject(), (String) serverIn.readObject(), uczen, nauczyciel, dyrektor);
            }
            if (instructionID == 110) {
                TeacherFrequency t = new TeacherFrequency();
                int classId = (int) serverIn.readObject();
                t.setList(connection, classId);
                // System.out.println(t);
                serverOut.writeObject(t);
            }
            if (instructionID == 111) {
                TeacherFrequency tF = new TeacherFrequency();
                int classId = (int) serverIn.readObject();
                int teacheId = (int) serverIn.readObject();
                Date data = Date.valueOf((LocalDate) serverIn.readObject());
                Vector<Integer> ids = new Vector<>();
                for (int i = 1; i < 6; i++) {
                    ids.add(tF.getLessonId(connection, classId, i, teacheId, data));
                }
                serverOut.writeObject(ids);
            }
            if (instructionID == 112) {
                TeacherFrequency tF = new TeacherFrequency();
                int present = (int) serverIn.readObject();
                int studentId = (int) serverIn.readObject();
                int lessonId = (int) serverIn.readObject();
                tF.chechl(connection, present, studentId, lessonId);

            }
            if (instructionID == 120) {
                TeacherMarks t = new TeacherMarks();
                int teacherId = (int) serverIn.readObject();
                int classId = (int) serverIn.readObject();
                t.setMarks(connection, teacherId, classId);
                TeacherFrequency tF = new TeacherFrequency();
                tF.setList(connection, classId);
                //System.out.println(t);
                serverOut.writeObject(t);
                serverOut.writeObject(tF.retList());
            }
            if (instructionID == 121) {
                TeacherMarks tM = new TeacherMarks();
                int mark = (int) serverIn.readObject();
                String description = (String) serverIn.readObject();
                int studentId = (int) serverIn.readObject();
                int teacherId = (int) serverIn.readObject();
                int subjectId = (int) serverIn.readObject();
                tM.addMark(connection, mark, description, studentId, teacherId, subjectId);

            }
            if (instructionID == 122) {
                TeacherMarks tM = new TeacherMarks();
                int markId = (int) serverIn.readObject();
                tM.deleteMarks(connection, markId);
            }
            if (instructionID == 131) {
                TeacherTestSchedule tTS = new TeacherTestSchedule();
                int teacherId = (int) serverIn.readObject();
                Date date1 = Date.valueOf((LocalDate) serverIn.readObject());
                Date date2 = Date.valueOf((LocalDate) serverIn.readObject());
                tTS.setSchedule(connection, teacherId, date1, date2);
                serverOut.writeObject(tTS.getDays());
            }
            if (instructionID == 132) {
                StudentTestSchedule sTS = new StudentTestSchedule();
                Date date1 = Date.valueOf((LocalDate) serverIn.readObject());
                int godz = (int) serverIn.readObject();
                String opis = (String) serverIn.readObject();
                int classId = (int) serverIn.readObject();
                sTS.addTest(connection, opis, godz, classId, date1);

            }
            if (instructionID == 133) {
                StudentTestSchedule sTS = new StudentTestSchedule();
                Date date1 = Date.valueOf((LocalDate) serverIn.readObject());
                int godz = (int) serverIn.readObject();

                int classId = (int) serverIn.readObject();
                sTS.deleteTest(connection, godz, classId, date1);

            }
            if (instructionID == 141) {
                TeacherClassWeekSchedule tCS = new TeacherClassWeekSchedule();
                int classId = (int) serverIn.readObject();
                Date date1 = Date.valueOf((LocalDate) serverIn.readObject());
                Date date2 = Date.valueOf((LocalDate) serverIn.readObject());
                tCS.setSchedule(connection, classId, date1, date2);
                serverOut.writeObject(tCS.getDays());
            }
            if (instructionID == 142) {
                TeacherWeekSchedule tS = new TeacherWeekSchedule();
                int teacherId = (int) serverIn.readObject();
                Date date1 = Date.valueOf((LocalDate) serverIn.readObject());
                Date date2 = Date.valueOf((LocalDate) serverIn.readObject());
                tS.setSchedule(connection, teacherId, date1, date2);
                serverOut.writeObject(tS.getDays());
            }
            if (instructionID == 210) {

                StudentFrequency sF = new StudentFrequency();
                int studentId = (int) serverIn.readObject();
                Date date1 = Date.valueOf((LocalDate) serverIn.readObject());
                Date date2 = Date.valueOf((LocalDate) serverIn.readObject());
                sF.setSchedule(connection, studentId, date1, date2);
                serverOut.writeObject(sF.getDays());

            }
            if (instructionID == 220) {
                StudentMarks sM = new StudentMarks();
                int studentId = (int) serverIn.readObject();
                sM.setMarks(connection, studentId);
                serverOut.writeObject(sM);
            }
            if (instructionID == 230) {
                StudentTestSchedule sT = new StudentTestSchedule();
                int classId = (int) serverIn.readObject();
                Date date1 = Date.valueOf((LocalDate) serverIn.readObject());
                Date date2 = Date.valueOf((LocalDate) serverIn.readObject());
                sT.setSchedule(connection, classId, date1, date2);
                serverOut.writeObject(sT.getDays());

            }
            if (instructionID == 240) {
                StudentClassWeekSchedule sC = new StudentClassWeekSchedule();
                int classId = (int) serverIn.readObject();
                Date date1 = Date.valueOf((LocalDate) serverIn.readObject());
                Date date2 = Date.valueOf((LocalDate) serverIn.readObject());
                sC.setSchedule(connection, classId, date1, date2);
                System.out.println(sC);
                serverOut.writeObject(sC.getDays());

            }
            if (instructionID == 311) {
                Adresy adresy = (Adresy) serverIn.readObject();
                AddPeople ap = new AddPeople();
                int id_adresy = ap.addAddress(connection, adresy);
                serverOut.writeObject(id_adresy);
                Uczniowie u = (Uczniowie) serverIn.readObject();
                ap.addStudent(connection, u);
            }
            if (instructionID == 320) {
                StudentList sL = new StudentList();
                sL.setList(connection);
                serverOut.writeObject(sL.getList());

            }
            if (instructionID == 321) {
                DeletePeople dP = new DeletePeople();
                int studentId = (int) serverIn.readObject();
                Uczniowie u = new Uczniowie();
                u.setId_ucznia(studentId);
                dP.deleteStudent(connection, u);
            }
            if (instructionID == 331) {
                Adresy adresy = (Adresy) serverIn.readObject();
                AddPeople ap = new AddPeople();
                int id_adresy = ap.addAddress(connection, adresy);
                serverOut.writeObject(id_adresy);
                Nauczyciele n = (Nauczyciele) serverIn.readObject();
                ap.addTeacher(connection, n);
            }
            if (instructionID == 340) {
                TeacherList tL = new TeacherList();
                tL.setList(connection);
                serverOut.writeObject(tL.getList());
            }
            if (instructionID == 341) {
                DeletePeople dP = new DeletePeople();
                int teacherId = (int) serverIn.readObject();
                Nauczyciele t = new Nauczyciele();
                t.setId_nauczyciela(teacherId);
                dP.deleteTeacher(connection, t);
            }
            if (instructionID == 350) {
                TeacherList tL = new TeacherList();
                tL.setList(connection);
                serverOut.writeObject(tL.getList());
            }
            if (instructionID == 351) {
                Wage wage = new Wage();
                int teacherId = (int) serverIn.readObject();
                double pensja = (double) serverIn.readObject();
                wage.updateWage(connection, pensja, teacherId);

            }
            if (instructionID == 360) {
                SubjectList sL = new SubjectList();
                TeacherList tL = new TeacherList();
                ClassList cL = new ClassList();
                RoomList rL = new RoomList();
                sL.setList(connection);
                tL.setList(connection);
                cL.setList(connection);
                rL.setList(connection);

                serverOut.writeObject(sL.getList());
                serverOut.writeObject(tL.getList());
                serverOut.writeObject(cL.getList());
                serverOut.writeObject(rL.getList());
            }
            if (instructionID == 361) {
                AddPlan aP = new AddPlan();
                int godz = (int) serverIn.readObject() + 1;
                LocalDate localDate = (LocalDate) serverIn.readObject();
                int przedmioty_id_przedmiotu = (int) serverIn.readObject();
                int nauczyciele_id_nauczyciela = (int) serverIn.readObject();
                int klasy_id_klasy = (int) serverIn.readObject();
                int sala_id_sala = (int) serverIn.readObject();
                aP.addPlan(connection, godz, localDate, przedmioty_id_przedmiotu, nauczyciele_id_nauczyciela, klasy_id_klasy, sala_id_sala);

            }
            /*if (instructionID == 1) {
                //System.out.println("DUPA "+instructionID);
                uczen = (Uczniowie) serverIn.readObject();
                System.out.println("ID wyslanego ucznia: " + uczen.getId_ucznia());
                System.out.println("Imie ucznia wyslanego na serwer: " + uczen.getImie());
                System.out.println("Nazwisko ucznia wyslanego na serwer: " + uczen.getNazwisko());
                System.out.println("Login ucznia wyslanego na serwer: " + uczen.getLogin());
                System.out.println("Haslo ucznia wyslanego na serwer: " + uczen.getHaslo());
                serverOut.writeObject("\n\n\n\n\n\nNapis");
            } else if (instructionID == 2) {
                nauczyciel = (Nauczyciele) serverIn.readObject();
                System.out.println("\n" + nauczyciel);
                break;
            }*/
        }


    }
}
