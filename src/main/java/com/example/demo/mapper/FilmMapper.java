package com.example.demo.mapper;

import com.example.demo.entity.Film;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FilmMapper {
    Film getFilmById(int id);
    List<Film> getAllFilm();
    int addFilm(Film film);
    int deleteFilmById(String id);
}
