import { Button, Grid, Box, TextField } from "@mui/material";
import AddressCard from "../AddressCard/AddressCard";
import { useDispatch, useSelector } from "react-redux";
import { createOrders } from "../../../redux/Order/orderSlice";
import { useNavigate } from "react-router-dom";
// import { useEffect } from "react";

const DeliveryAddress = () => {
  const dispatch = useDispatch();
  const navigate = useNavigate();

  const userData = useSelector((state) => state.authReducer);

  const perviousAddressHandler = (add) => {
    const orderData = {
      address: add,
      navigate,
    };
    dispatch(createOrders(orderData));
  };
  const handleSubmit = (event) => {
    event.preventDefault();

    console.log("address");
    const data = new FormData(event.currentTarget);
    const shipping_details = {
      firstName: data.get("firstName"),
      lastName: data.get("lastName"),
      streetAddress: data.get("address"),
      city: data.get("city"),
      province: data.get("state"),
      zipCode: data.get("pincode"),
      mobile: data.get("mobileno"),
    };
    const orderData = {
      address: shipping_details,
      navigate,
    };
    dispatch(createOrders(orderData));
  };
  return (
    <div>
      <Grid
        container
        columnSpacing={2}>
        <Grid
          item
          xs={12}
          lg={5}
          className="border rounded-e-md shadow-md h-[30.5rem] overflow-y-scroll">
          <div className="p-5 py-7 border-b cursor-pointer">
            {userData?.user?.address?.map((add, index) => {
              return (
                <div
                  key={index}
                  className="m-1 rounded ">
                  <AddressCard address={add} />
                  <Button
                    onClick={() => perviousAddressHandler(add)}
                    sx={{
                      mt: 2,
                      bgcolor: "RGB(145,85,253)",
                      ":hover": {
                        bgcolor: "#a16ffd",
                      },
                    }}
                    variant="contained"
                    size="large">
                    Deliver Here
                  </Button>
                </div>
              );
            })}
          </div>
        </Grid>
        <Grid
          item
          xs={12}
          lg={7}>
          <Box className="border rounded-s-md shadow-md p-5">
            <form onSubmit={handleSubmit}>
              <Grid
                container
                spacing={3}>
                <Grid
                  item
                  xs={12}
                  sm={6}>
                  <TextField
                    required
                    id="firstName"
                    name="firstName"
                    label="First Name"
                    fullWidth
                    autoComplete="given-name"
                  />
                </Grid>
                <Grid
                  item
                  xs={12}
                  sm={6}>
                  <TextField
                    required
                    id="lastName"
                    name="lastName"
                    label="Last Name"
                    fullWidth
                    autoComplete="given-name"
                  />
                </Grid>
                <Grid
                  item
                  xs={12}>
                  <TextField
                    required
                    id="address"
                    name="address"
                    label="Shipping Address"
                    fullWidth
                    autoComplete="Address"
                    multiline
                    rows={4}
                  />
                </Grid>
                <Grid
                  item
                  xs={12}
                  sm={6}>
                  <TextField
                    required
                    id="city"
                    name="city"
                    label="City"
                    fullWidth
                    autoComplete="city"
                  />
                </Grid>
                <Grid
                  item
                  xs={12}
                  sm={6}>
                  <TextField
                    required
                    id="state"
                    name="state"
                    label="State/Province/Region"
                    fullWidth
                    autoComplete="State"
                  />
                </Grid>
                <Grid
                  item
                  xs={12}
                  sm={6}>
                  <TextField
                    required
                    id="pincode"
                    name="pincode"
                    label="Zipcode/Pincode"
                    fullWidth
                    autoComplete="pincode"
                  />
                </Grid>
                <Grid
                  item
                  xs={12}
                  sm={6}>
                  <TextField
                    required
                    id="mobileno"
                    name="mobileno"
                    label="Mobile Number"
                    fullWidth
                    autoComplete="Mobile Number"
                  />
                </Grid>

                <Grid
                  item
                  xs={12}
                  sm={6}>
                  <Button
                    type="submit"
                    sx={{
                      py: 1.5,
                      mt: 1,
                      ":hover": {
                        bgcolor: "#a16ffd",
                      },
                      bgcolor: "RGB(145,85,253)",
                    }}
                    variant="contained"
                    size="large">
                    Deliver Here
                  </Button>
                </Grid>
              </Grid>
            </form>
          </Box>
        </Grid>
      </Grid>
    </div>
  );
};

export default DeliveryAddress;
