import { Box, Grid } from "@mui/material";
import AddressCard from "../AddressCard/AddressCard";
import OrderTracking from "./OrderTracking";
import { deepPurple } from "@mui/material/colors";
import { StarBorder } from "@mui/icons-material";
const OrderDetails = () => {
  return (
    <div className="px-5 lg:px-20 py-5">
      <div>
        <h1 className="font-semibold text-xl py-7">Delivery Address</h1>
        <AddressCard />
      </div>
      <div className="py-20">
        <OrderTracking activeStep={1} />
      </div>
      <Grid
        container
        className="space-y-5">
        {[1, 1, 1, 1].map((i, index) => (
          <Grid
            key={index}
            item
            container
            className="shadow-xl space-y-5 rounded-md p-5 border"
            sx={{ alignItems: "center", justifyContent: "space-between" }}>
            <Grid
              item
              xs={6}>
              <div className="flex items-center space-x-4">
                <img
                  className="w-[5rem] h-[5rem] object-cover object-top"
                  src="https://rukminim1.flixcart.com/image/612/612/xif0q/kurta/l/f/r/xl-k-spl668-yellow-sg-leman-original-imagznqcrahgq9rf.jpeg?q=70"></img>
                <div className="space-y-2 ml-5">
                  <p className="font-semibold ">
                    Men Embroidered Jacquered Straight Kurta
                  </p>
                  <p className="space-x-2 opacity-50 text-sm">
                    {" "}
                    <span>Color: pink</span> <span>Size: M</span>
                  </p>
                  <p>Sg LeMan</p>
                  <p>$ 50</p>
                </div>
              </div>
            </Grid>
            <Grid item>
              <Box sx={{ color: deepPurple[500] }}>
                <StarBorder
                  sx={{ fontSize: "2rem" }}
                  className="px-2 text-5xl"
                />
                <span> Rate & Review Product</span>
              </Box>
            </Grid>
          </Grid>
        ))}
      </Grid>
    </div>
  );
};

export default OrderDetails;
