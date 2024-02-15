    package examen.examen.Controller;

    import examen.examen.Model.Genre;
    import examen.examen.Model.Movies;
    import examen.examen.Service.GenresService;
    import examen.examen.Service.MoviesService;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.web.bind.annotation.*;

    import java.util.Base64;
    import java.util.List;
    import java.util.Map;

    @RestController
    @RequestMapping("/api/movies")
    @CrossOrigin(origins = "http://localhost:5173")
    public class MoviesController {

        @Autowired
        MoviesService moviesService;

        @Autowired
        GenresService genresService;






        @GetMapping("/movies")
        public List<Movies> getMovies() {
            return moviesService.getAllMovies();
        }

        @PostMapping("/genres")
        public Genre addGenre(@RequestBody Genre genre) {
            return genresService.saveGenre(genre);
        }

        @GetMapping("/genres")
        public List<Genre> getGenres() {
            return genresService.getAllGenres();
        }

        // Método para eliminar una película por su nombre
        @DeleteMapping("/movies/{name}")
        public String deleteMovieByName(@PathVariable String name) {
            Movies movie = moviesService.getName(name);
            if (movie == null) {
                return "Movie with name " + name + " not found";
            }
            moviesService.deleteMovie(movie.getId());
            return "Movie with name " + name + " deleted";
        }


        @PutMapping("/movies/{id}")
        public String updateMovie(@PathVariable Long id, @RequestBody Movies updatedMovie) {
            Movies movie = moviesService.getMovieById(id);
            movie.setName(updatedMovie.getName());
            movie.setYear(updatedMovie.getYear());
            movie.setGenre(updatedMovie.getGenre());
            moviesService.saveMovie(movie);
            return "Movie with ID " + id + " updated";
        }


        @PostMapping("/registermovies")
        public Movies addMovie(@RequestBody Map<String, Object> movieData) {
            System.out.println(movieData);
            try {
                System.out.println(movieData.get("name"));
                System.out.println(movieData.get("genre"));
                System.out.println(movieData.get("year"));
                System.out.println(movieData.get("image"));

                String name = (String) movieData.get("name");
                // Obtener los datos del género del mapa y crear una instancia de Genre
                Map<String, Object> genreData = (Map<String, Object>) movieData.get("genre");
                Genre genre = new Genre((Integer) genreData.get("id"), (String) genreData.get("name"));

                int year = Integer.parseInt((String) movieData.get("year"));
                byte[] image = Base64.getDecoder().decode((String) movieData.get("image"));

                // Convierte la imagen a Base64 si es necesario
                String base64Image = Base64.getEncoder().encodeToString(image);

                // Crea una nueva instancia de Movies
                Movies movie = new Movies(name, genre, year, base64Image);
                System.out.println(movie);

                // Guarda la película en la base de datos utilizando el servicio
                return moviesService.saveMovie(movie);

            } catch (Exception e) {
                e.printStackTrace(); // Esto imprimirá el rastreo de la pila en la consola del servidor
                throw new RuntimeException("Error al guardar la película: " + e.getMessage());
            }
        }



    }

