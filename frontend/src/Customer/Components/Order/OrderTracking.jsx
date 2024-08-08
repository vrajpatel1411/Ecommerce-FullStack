import { Stepper, Step, StepLabel } from "@mui/material";
// 31:41

const OrderTracking = ({ activeStep }) => {
  const steps1 = [
    "Placed",
    "Order Confirmed",
    "Shipped",
    "Out for Delivery",
    "Delivered",
  ];
  return (
    <div className="w-full">
      <Stepper
        activeStep={activeStep}
        alternativeLabel>
        {steps1.map((label, index) => {
          return (
            <Step key={index}>
              <StepLabel sx={{ color: "#9155FD", fontSize: "44px" }}>
                {label}
              </StepLabel>
            </Step>
          );
        })}
      </Stepper>
    </div>
  );
};

export default OrderTracking;
