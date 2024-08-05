import Carousel from "../Components/HomeCaraousal/carousel";
import HomeSectionCaraousel from "../Components/HomeSection/HomeSectionCarousel";
import mens_kurta from "../../Data/mens_kurta";

export const Homepage = () => {
  return (
    <div>
      <div>
        <Carousel />
      </div>
      <div className="py-20 space-y-10 flex flex-col justify-center px-5 lg:px-10">
        <HomeSectionCaraousel
          data={mens_kurta}
          sectionName="Men's Kurta"
        />
        <HomeSectionCaraousel
          data={mens_kurta}
          sectionName="Men's Shoes"
        />
        <HomeSectionCaraousel
          data={mens_kurta}
          sectionName={"Men's Shirt"}
        />
        <HomeSectionCaraousel
          data={mens_kurta}
          sectionName={"Women's Saree"}
        />
        <HomeSectionCaraousel
          data={mens_kurta}
          sectionName={"Women's Dress"}
        />
      </div>
    </div>
  );
};
