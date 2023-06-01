package com.example.studentmanagement.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "result")
public class Result {
    @Id
    @Column(name = "result_id", nullable = false)
    private Integer id;

    @Column(name = "first_year_score")
    private Float firstYearScore;

    @Column(name = "second_year_score")
    private Float secondYearScore;

    @Column(name = "third_year_score")
    private Float thirdYearScore;

    @Column(name = "fourth_year_score")
    private Float fourthYearScore;

    @Column(name = "fifth_year_score")
    private Float fifthYearScore;

    @Column(name = "rank_result")
    private Integer rankResult;

    @Column(name = "gap")
    private Float gap;

}