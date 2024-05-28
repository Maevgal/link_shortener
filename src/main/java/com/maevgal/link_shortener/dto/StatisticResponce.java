package com.maevgal.link_shortener.dto;

import lombok.Data;

import java.util.List;

@Data
public class StatisticResponce {
    private List<LinkStatsDTO> statistic;
}
