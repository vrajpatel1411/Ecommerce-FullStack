import { Grid } from "@mui/material";
import AdjustIcon from "@mui/icons-material/Adjust";
import { useNavigate } from "react-router-dom";
const OrderCard = () => {
  const navigate = useNavigate();
  return (
    <div
      onClick={() => {
        navigate(`/account/order/${4}`);
      }}
      className="p-5 shadow-sm hover:shadow-2xl rounded-lg shadow-black">
      <Grid
        container
        spacing={2}
        sx={{ justifyContent: "space-between" }}>
        <Grid
          item
          sx={6}>
          <div className="flex cursor-pointer ">
            <img
              className=" w-[5rem] h-[5rem] object-cover  object-top"
              src="https://rukminim1.flixcart.com/image/612/612/xif0q/kurta/x/f/6/xxl-new-white-nofilter-original-imaghzggudfezpr8.jpeg?q=70"></img>{" "}
            <div className="ml-5 space-y-2">
              <p className="">Men Slim Mid Rise Black Jeans</p>
              <p className="opacity-50 text-xs font-semibold ">Size: M</p>
              <p className="opacity-50 text-xs font-semibold ">Color:Black</p>
            </div>
          </div>
        </Grid>
        <Grid
          item
          xs={2}>
          <p className="">$ 20</p>
        </Grid>
        <Grid
          item
          xs={4}>
          {true && (
            <div>
              <p>
                <AdjustIcon
                  className="text-green-600 mr-2  text-sm"
                  sx={{ width: "15px", height: "15px" }}
                />
                <span>Delivered on March 03</span>
              </p>
              <p className="text-xs ">Your Item Has been Delivered</p>
            </div>
          )}
          {false && (
            <p>
              <AdjustIcon />
              <span>Expected Delivery on March 03</span>
            </p>
          )}
        </Grid>
      </Grid>
    </div>
  );
};

export default OrderCard;
