<template>
  <div>
    <b-carousel
      id="carousel-1"
      v-model="slide"
      :interval="4000"
      controls
      indicators
      background="#ababab"
      img-width="1024"
      img-height="480"
      style="text-shadow: 1px 1px 2px #333;"
      @sliding-start="onSlideStart"
      @sliding-end="onSlideEnd"
    >
      <!-- Iterar sobre el arreglo de datos para generar las tarjetas -->
      <b-carousel-slide v-for="(item, index) in carouselItems" :key="index" :caption="item.caption" :text="item.text" :img-src="item.imgSrc">
        <!-- Puedes agregar contenido adicional dentro de las tarjetas aquí -->
      </b-carousel-slide>
    </b-carousel>

    <p class="mt-4">
      Slide #: {{ slide }}<br>
      Sliding: {{ sliding }}
    </p>
  </div>
</template>

<script>
export default {
  data() {
    return {
      slide: 0,
      sliding: null,
      carouselItems: [
        {
          caption: "First slide",
          text: "Nulla vitae elit libero, a pharetra augue mollis interdum.",
          imgSrc: "https://picsum.photos/1024/480/?image=52"
        },
        {
          caption: "Second slide",
          text: "Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
          imgSrc: "https://picsum.photos/1024/480/?image=54"
        },
        {
          caption: "Third slide",
          text: "Sed posuere consectetur est at lobortis.",
          imgSrc: "https://picsum.photos/1024/480/?image=58"
        }
        // Agrega más objetos al arreglo según sea necesario
      ]
    };
  },
  methods: {
    onSlideStart(slide) {
      this.sliding = true;
    },
    onSlideEnd(slide) {
      this.sliding = false;
    }
  }
};
</script>

<style scoped>
/* Estilos adicionales según sea necesario */
</style>
