"use client";
import Link from "next/link";
import { useUser } from "@auth0/nextjs-auth0/client";
import Hero from "@/components/landing/Hero";
import Rules from "@/components/landing/Rules";
import ClubPrompt from "@/components/landing/ClubPrompt";

const Landing = () => {
  const { user, isLoading, error } = useUser();

  if (isLoading) <div className="text-center mt-10">Loading...</div>;
  if (error) <div className="text-center mt-10">{error.message}</div>;

  return (
    <div className="container mx-auto py-12">
      <Hero />
      <Rules />
      <ClubPrompt />
    </div>
  );
};

export default Landing;
