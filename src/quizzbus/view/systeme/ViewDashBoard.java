package quizzbus.view.systeme;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import jakarta.inject.Inject;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import jfox.javafx.util.UtilFX;
import jfox.javafx.view.ControllerAbstract;
import quizzbus.data.Compte;
import quizzbus.data.Quizz;
import quizzbus.view.compte.ModelCompte;
import quizzbus.view.quizz.ModelQuizz;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.scene.chart.PieChart.Data;
import javafx.collections.FXCollections;

public class ViewDashBoard extends ControllerAbstract {

    @FXML
    private ListView<Quizz> lvQuizz;

    @FXML
    private PieChart PieQuizz;

    @FXML
    private PieChart PieUtilisateur;

    @FXML
    private TextField TfNombre;

    @FXML
    private TextField TfRecherche;

    @FXML
    private BarChart<String, Number> chartstatglo;

    @FXML
    private ListView<Compte> lvUtilisateur;

    @FXML
    private Text lville;

    @FXML
    private Pane photo;
    
    @FXML
    private TextField lbAdmin;

    @Inject
    private ModelQuizz modelQuizz;
    @Inject
	private ModelConnexion modelConnexion;
    @Inject
    private ModelCompte modelCompte;

    private Map<String, Integer> map = new HashMap<>();

    @FXML
    private void initialize() {
        // Partie liste
        // ListView des comptes

        PieQuizz.setData(getChartData());
        PieUtilisateur.setData(getChartDat());

        System.out.print("ttttttttttttttt" + modelQuizz.getList().size());
        lvQuizz.setItems(modelQuizz.getList());
        UtilFX.setCellFactory(lvQuizz, "description");
        bindBidirectional(lvQuizz, modelQuizz.currentProperty(), modelQuizz.flagRefreshingListProperty());

        lvUtilisateur.setItems(modelCompte.getList());
        UtilFX.setCellFactory(lvUtilisateur, "nom");
        bindBidirectional(lvUtilisateur, modelCompte.currentProperty(), modelCompte.flagRefreshingListProperty());

        TfNombre.setText(modelCompte.getList().size() + " ");
        updateBarChart();
        
        lbAdmin.setText(modelConnexion.getCompteActif().getNom() + " " +modelConnexion.getCompteActif().getPrenom());
    }

    @FXML
    private void updatePieChart() {
        PieQuizz.setData(getChartData());
        PieUtilisateur.setData(getChartDat());
    }

    private void obtenirStatSF() {
        map.clear();
        int occurencecelib = 0;
        int occurencemarie = 0;
        int occurenceveuf = 0;
        int occurencedivorce = 0;
        int occurencepasce = 0;
        modelQuizz.refreshList();
        System.out.print("ttttttttttttttt" + modelQuizz.getList().size());

        for (Quizz p : modelQuizz.getList()) {
            if (p.getTheme().getNom().equals("Connaissance générale")) {
                occurencecelib++;
            }
            if (p.getTheme().getNom().equals("Cycles de l’eau")) {
                occurencemarie++;
            }
            if (p.getTheme().getNom().equals("Faune / Flore")) {
                occurencedivorce++;
            }
            if (p.getTheme().getNom().equals("Les milieux et habitats")) {
                occurenceveuf++;
            }
            if (p.getTheme().getNom().equals("Consommation, économie et bons gestes")) {
                occurencepasce++;
            }
        }
        System.out.println(occurencecelib + " " + occurencemarie + " " + occurencedivorce + " " + occurenceveuf + " " + occurencepasce);
        map.put("Connaissance générale", occurencecelib);
        map.put("Cycles de l’eau", occurencemarie);
        map.put("Faune / Flore", occurencedivorce);
        map.put("Les milieux et habitats", occurenceveuf);
        map.put("Consommation, économie et bons gestes", occurencepasce);
    }

    private void obtenirStatS() {
        map.clear();
        int occurencecelib = 0;
        int occurencemarie = 0;
        int occurenceveuf = 0;
        modelCompte.refreshList();
        System.out.print("ttttttttttttttt" + modelCompte.getList().size());

        for (Compte p : modelCompte.getList()) {
            if (p.getVille().equals("lille")) {
                occurencecelib++;
            }
            if (p.getVille().equals("bordeaux")) {
                occurencemarie++;
            }
            if (p.getVille().equals("lyon")) {
                occurenceveuf++;
            }
            if (p.getVille().equals("paris")) {
                occurenceveuf++;
            }
        }
        System.out.println(occurencecelib + " " + occurencemarie + " " + occurenceveuf);
        map.put("bordeaux", occurencecelib);
        map.put("lille", occurencemarie);
        map.put("paris", occurenceveuf);
        map.put("lyon", occurenceveuf);
    }

    @FXML
    private ObservableList<PieChart.Data> getChartData() {
        obtenirStatSF();
        ObservableList<Data> answer = FXCollections.observableArrayList();
        for (String s : map.keySet()) {
            answer.add(new PieChart.Data(s, 100 * ((double) map.get(s) / (double) map.size())));
        }
        return answer;
    }

    @FXML
    private ObservableList<PieChart.Data> getChartDat() {
        obtenirStatS();
        ObservableList<Data> answer = FXCollections.observableArrayList();
        for (String s : map.keySet()) {
            answer.add(new PieChart.Data(s, 100 * ((double) map.get(s) / (double) map.size())));
        }
        return answer;
    }
    
 // New method to update the StackedBarChart with quiz statistics by theme
    private void updateBarChart() {
        chartstatglo.getData().clear();
        
        Map<String, Integer> themeCountMap = new HashMap<>();
        modelQuizz.refreshList();
        
        for (Quizz quizz : modelQuizz.getList()) {
            String themeName = quizz.getTheme().getNom();
            themeCountMap.put(themeName, themeCountMap.getOrDefault(themeName, 0) + 1);
        }
        
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        for (Map.Entry<String, Integer> entry : themeCountMap.entrySet()) {
            series.getData().add(new XYChart.Data<>(entry.getKey(), entry.getValue()));
        }
        
        chartstatglo.getData().add(series);
    }
    
 // Method to filter the quiz list based on the theme entered in the search field
    private void filterQuizzList(String theme) {
        ObservableList<Quizz> filteredList;
        if (theme == null || theme.isEmpty()) {
            filteredList = modelQuizz.getList();
        } else {
            filteredList = modelQuizz.getList().stream()
                .filter(quizz -> quizz.getTheme().getNom().contains(theme))
                .collect(Collectors.toCollection(FXCollections::observableArrayList));
        }
        lvQuizz.setItems(filteredList);
    }

    // Method to handle the ActionEvent when text is entered in the search field
    @FXML
    void TfRecherche(ActionEvent event) {
        String theme = TfRecherche.getText();
        filterQuizzList(theme);
        System.out.println("bonjour");
        
    }
}
