/* eslint-disable react/jsx-key */
import AliceCarousel from "react-alice-carousel";
import "react-alice-carousel/lib/alice-carousel.css";
import { homeCarouselData } from "./carouselData";

const Carousel = () => {
  const items = homeCarouselData.map((item) => (
    <img
      className="cursor-pointer "
      role="presentation"
      src={item.image}></img>
  ));

  return (
    <AliceCarousel
      items={items}
      disableButtonsControls
      autoPlay
      autoPlayInterval={1000}
      infinite
    />
  );
};

export default Carousel;
