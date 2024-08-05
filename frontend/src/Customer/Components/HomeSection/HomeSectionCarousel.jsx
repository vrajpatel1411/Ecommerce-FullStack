import "react-alice-carousel/lib/alice-carousel.css";
import HomeSectionCard from "./HomeSectionCard";
import KeyboardArrowLeftIcon from "@mui/icons-material/KeyboardArrowLeft";
import { Button } from "@mui/material";

import "slick-carousel/slick/slick.css";
import "slick-carousel/slick/slick-theme.css";
import Slider from "react-slick";
import { useState } from "react";

const HomeSectionCaraousel = ({ data, sectionName }) => {
  const [activeSlide, setActiveSlide] = useState(0);

  const SampleNextArrow = (props) => {
    const { onClick } = props;
    if (activeSlide == data.length - 5) {
      return null;
    }
    return (
      <Button
        variant="contained"
        className="z-50 bg-white"
        sx={{
          position: "absolute",
          top: "8rem",
          right: "0rem",
          transform: "translateX(50%) rotate(90deg)",
          bgcolor: "white",
        }}
        aria-label="next"
        onClick={onClick}>
        <KeyboardArrowLeftIcon
          sx={{ transform: "rotate(90deg)", color: "black" }}
        />
      </Button>
    );
  };

  const SamplePrevArrow = (props) => {
    const { onClick } = props;
    if (activeSlide == 0) {
      return null;
    }
    return (
      <Button
        variant="contained"
        className="z-50 bg-white"
        onClick={onClick}
        sx={{
          position: "absolute",
          top: "8rem",
          left: "0rem",
          transform: "translateX(-50%) rotate(90deg)",
          bgcolor: "white",
        }}
        aria-label="next">
        <KeyboardArrowLeftIcon
          sx={{ transform: "rotate(-90deg)", color: "black" }}
        />
      </Button>
    );
  };

  const settings = {
    dots: false,
    infinite: false,
    slidesToShow: 5.5,
    slidesToScroll: 1,
    nextArrow: <SampleNextArrow />,
    prevArrow: <SamplePrevArrow />,
    afterChange: (current) => setActiveSlide(current),
    responsive: [
      {
        breakpoint: 1200,
        settings: {
          slidesToShow: 5.5,
          slidesToScroll: 1,
        },
      },
      {
        breakpoint: 1150,
        settings: {
          slidesToShow: 4.5,
          slidesToScroll: 1,
        },
      },
      {
        breakpoint: 1024,
        settings: {
          slidesToShow: 3.5,
          slidesToScroll: 1,
        },
      },
      {
        breakpoint: 768,
        settings: {
          slidesToShow: 2,
          slidesToScroll: 1,
        },
      },
      {
        breakpoint: 480,
        settings: {
          slidesToShow: 1.5,
          slidesToScroll: 1,
        },
      },
    ],
  };

  return (
    <div className="">
      <h2 className="text-2xl font-extrabold text-gray-800 py-5">
        {sectionName}
      </h2>
      <div className=" border relative p-5">
        <Slider {...settings}>
          {data.slice(0, 15).map((item, index) => (
            <HomeSectionCard
              key={index}
              product={item}
            />
          ))}
        </Slider>
      </div>
    </div>
  );
};

export default HomeSectionCaraousel;
