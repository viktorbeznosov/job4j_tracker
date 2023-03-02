package ru.job4j.hmap;

import java.util.*;

public class AnalyzeByMap {
    public static double averageScore(List<Pupil> pupils) {
        int count = 0;
        double total = 0;
        for (Pupil pupil: pupils) {
            for (Subject subject: pupil.subjects()) {
                total += subject.score();
                count++;
            }
        }
        return total / count;
    }

    public static List<Label> averageScoreByPupil(List<Pupil> pupils) {
        List<Label> labels = new ArrayList<>();
        for (Pupil pupil: pupils) {
            double total = 0;
            int count = 0;
            for (Subject subject: pupil.subjects()) {
                total += subject.score();
                count++;
            }
            double average = total / count;
            labels.add(new Label(pupil.name(), average));
        }
        return labels;
    }

    public static List<Label> averageScoreBySubject(List<Pupil> pupils) {
        List<Label> labels = new ArrayList<>();
        Map<String, Double> subjects = new LinkedHashMap<>();

        for (Pupil pupil: pupils) {
            for (Subject subject: pupil.subjects()) {
                Double score = subjects.containsKey(subject.name())
                        ? subjects.get(subject.name()).doubleValue() + subject.score() : subject.score();
                subjects.put(subject.name(), score);
            }
        }
        for (Map.Entry<String, Double> subject: subjects.entrySet()) {
            labels.add(new Label(subject.getKey(), subject.getValue() / pupils.size()));
        }

        return labels;
    }

    public static Label bestStudent(List<Pupil> pupils) {
        List<Label> list = new ArrayList<>();
        for (Pupil pupil: pupils) {
            double sum = 0;
            for (Subject subject: pupil.subjects()) {
                sum += subject.score();
            }
            list.add(new Label(pupil.name(), sum));
        }

        list.sort(Comparator.naturalOrder());

        return list.get(list.size() - 1);
    }

    public static Label bestSubject(List<Pupil> pupils) {
        Map<String, Double> subjects = new HashMap<>();
        List<Label> labels = new ArrayList<>();
        for (Pupil pupil: pupils) {
            for (Subject subject: pupil.subjects()) {
                Double score = subjects.containsKey(subject.name())
                        ? subjects.get(subject.name()).doubleValue() + subject.score() : subject.score();
                subjects.put(subject.name(), score);
            }
        }
        for (Map.Entry<String, Double> subject: subjects.entrySet()) {
            labels.add(new Label(subject.getKey(), subject.getValue()));
        }

        labels.sort(Comparator.naturalOrder());

        return labels.get(labels.size() - 1);
    }
}
