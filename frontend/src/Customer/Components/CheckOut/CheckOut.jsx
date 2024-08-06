import Box from "@mui/material/Box";
import Stepper from "@mui/material/Stepper";
import Step from "@mui/material/Step";
import StepLabel from "@mui/material/StepLabel";
import Button from "@mui/material/Button";
import Typography from "@mui/material/Typography";
import { useState } from "react";
import { useLocation } from "react-router-dom";
import DeliveryAddress from "./DeliveryAddress";
import OrderSummary from "./OrderSummary";
const steps = ["Login", "Delivery Address", "Orders Summary", "Payment"];

export default function CheckOut() {
  const [activeStep, setActiveStep] = useState(0);
  const location = useLocation();
  const querySearch = new URLSearchParams(location.search);

  const step = parseInt(querySearch.get("step"));

  const handleNext = () => {
    setActiveStep((prevActiveStep) => prevActiveStep + 1);
  };

  const handleBack = () => {
    setActiveStep((prevActiveStep) => prevActiveStep - 1);
  };

  return (
    <div className="px-10 lg:px-20">
      <Box sx={{ width: "100%" }}>
        <Stepper activeStep={step}>
          {steps.map((label, index) => {
            const stepProps = {};
            const labelProps = {};

            return (
              <Step
                key={index}
                {...stepProps}>
                <StepLabel {...labelProps}>{label}</StepLabel>
              </Step>
            );
          })}
        </Stepper>
        {activeStep === steps.length ? (
          <>
            <Typography sx={{ mt: 2, mb: 1 }}>
              All steps completed - you&apos;re finished
            </Typography>
            <Box sx={{ display: "flex", flexDirection: "row", pt: 2 }}>
              <Box sx={{ flex: "1 1 auto" }} />
            </Box>
          </>
        ) : (
          <>
            {/* <Typography sx={{ mt: 2, mb: 1 }}>{form[activeStep]}</Typography> */}
            <Box sx={{ display: "flex", flexDirection: "row", pt: 2 }}>
              <Button
                color="inherit"
                disabled={activeStep === 0}
                onClick={handleBack}>
                Back
              </Button>
              <Box sx={{ flex: "1 1 auto" }} />

              <Button onClick={handleNext}>
                {activeStep === steps.length - 1 ? "Finish" : "Next"}
              </Button>
            </Box>
            <div className="mt-10">
              {step == 2 ? <DeliveryAddress /> : null}
              {step == 3 ? <OrderSummary /> : null}
            </div>
          </>
        )}
      </Box>
    </div>
  );
}
