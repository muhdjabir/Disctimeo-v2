import { Button } from "../ui/button";

const Rules = () => {
    return (
        <div className="w-full bg-darkgrey py-16 px-4 rounded-lg">
            <div className="max-w-[1240px] mx-auto grid md:grid-cols-2">
                <img className="w-[500px] mx-auto my-4" src="/blue_logo.png" alt="/" />
                <div className="flex flex-col justify-center gap-10">
                    <h1 className="md:text-4xl sm:text-3xl text-2xl font-bold text-black py-2 ">
                        New to Ultimate?
                    </h1>
                    <p className="text-black text-xl text-right text-montserrat font-normal">
                        Visit the World Flying Disc Federation Website to learn
                        more about different types of Flying Disc Sports as well
                        as Spirit of The Game, central to enjoyment of Ultimate
                        Flying Disc Competitions
                    </p>
                    <div>
                        <Button>
                            <a
                                href="https://rules.wfdf.sport/"
                                target="_blank"
                                className=" px-5 font-medium my-6 mx-auto md:mx-0 py-3"
                            >
                                WFDF Rulebook
                            </a>
                        </Button>
                    </div>
                </div>
            </div>
        </div>
    );
}

export default Rules;